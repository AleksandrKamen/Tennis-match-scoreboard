package util.repository_util;

import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class BaseRepository<K extends Serializable,E > implements Repository<K,E>{

    private final Class<E> clazz;
    @Getter
    private final EntityManager entityManager;
    @Override
    public E save(E entity) {
        entityManager.persist(entity);
        return entity;
    }
    @Override
    public Optional<E> find(K id) {
        return Optional.ofNullable(entityManager.find(clazz, id));
    }

    @Override
    public List<E> findAll() {
        var criteriaQuery = entityManager.getCriteriaBuilder().createQuery(clazz);
        criteriaQuery.from(clazz);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public E update(E entity) {
       return entityManager.merge(entity);
    }
    @Override
    public void delete(K id) {
        entityManager.remove(id);
        entityManager.flush();
    }
}
