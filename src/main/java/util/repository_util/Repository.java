package util.repository_util;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Repository<K extends Serializable,E>{

   E save(E entity);
   Optional<E> find(K id);
   List<E> findAll();
   E update(E entity);
   void delete(K id);

}
