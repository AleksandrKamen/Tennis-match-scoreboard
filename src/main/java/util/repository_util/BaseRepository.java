package util.repository_util;

import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public abstract class BaseRepository<K extends Serializable,E > implements Repository<K,E>{

    private final Class<E> clazz;

    @Override
    public E save(E entity) {
        Transaction transaction = null;
        try (var session = HibernateUtil.getSession()){
            transaction = session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        } catch (HibernateException hibernateException){
              if (transaction != null && transaction.isActive()){
                  transaction.rollback();
              }
        }
        return entity;
    }
    @Override
    public Optional<E> find(K id) {
        try (var session = HibernateUtil.getSession()){
           var transaction = session.beginTransaction();
            E mabyObject = session.find(clazz, id);
            transaction.commit();
            return Optional.ofNullable(mabyObject);
        }
    }

    @Override
    public List<E> findAll() {
        try (var session = HibernateUtil.getSession()){
           var transaction = session.beginTransaction();
            var criteriaQuery = session.getCriteriaBuilder().createQuery(clazz);
            criteriaQuery.from(clazz);
            List<E> resultList = session.createQuery(criteriaQuery).getResultList();
            transaction.commit();
            return resultList;
        }
    }
 }
