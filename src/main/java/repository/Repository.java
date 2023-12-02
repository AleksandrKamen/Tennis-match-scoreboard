package repository;

import java.util.List;
import java.util.Optional;

public interface Repository<K,E>{

   E save(E entity);
   Optional<E> find(K id);
   List<E> findAll();
   E update(E entity);
   boolean delete(K id);

}
