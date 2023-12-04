package controller;

import controller.CurrentMatchEntity;
import jakarta.persistence.EntityManager;
import repository.BaseRepository;

public class CurrentMatchesRepository extends BaseRepository<Integer, CurrentMatchEntity> {
    public CurrentMatchesRepository(EntityManager entityManager){
        super(CurrentMatchEntity.class, entityManager);
    }
}
