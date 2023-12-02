package repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class BaseRepository<K extends Serializable,E > implements Repository<K,E>{

    private final Class<E> clazz;
    private final SessionFactory sessionFactory;
    @Override
    public E save(E entity) {
        var session = sessionFactory.openSession();
        session.persist(entity);
        return entity;
    }

    @Override
    public Optional<E> find(K id) {
        var session = sessionFactory.openSession();
        return Optional.ofNullable(session.find(clazz, id));
    }

    @Override
    public List<E> findAll() {
        var session = sessionFactory.openSession();
        var criteriaQuery = session.getCriteriaBuilder().createQuery(clazz);
        criteriaQuery.from(clazz);
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public E update(E entity) {
        var session = sessionFactory.openSession();
        session.merge(entity);
        return entity;
    }
    @Override
    public boolean delete(K id) {
        var session = sessionFactory.openSession();
        session.remove(id);
        session.flush();
        return false;
    }
}
