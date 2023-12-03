package repository;

import entity.MatchesEntity;
import jakarta.persistence.EntityManager;

public class MathesRepository extends BaseRepository<Integer, MatchesEntity> {
    public MathesRepository(EntityManager entityManager){
        super(MatchesEntity.class, entityManager);
    }

}
