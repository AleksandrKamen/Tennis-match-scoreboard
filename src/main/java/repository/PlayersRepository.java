package repository;

import entity.PlayersEntity;
import entity.PlayersEntity_;
import jakarta.persistence.EntityManager;
import java.util.Optional;

public class PlayersRepository extends BaseRepository<Integer, PlayersEntity>{

    public PlayersRepository(EntityManager entityManager){
        super(PlayersEntity.class, entityManager);
    }
    public Optional<PlayersEntity> findByName(String name){
        var entityManager = getEntityManager();
        var cb = entityManager.getCriteriaBuilder();
        var criteria = cb.createQuery(PlayersEntity.class);
        var players = criteria.from(PlayersEntity.class);
        criteria.select(players)
                .where(cb.equal(players
                .get(PlayersEntity_.NAME),name));
        return Optional.ofNullable(entityManager.createQuery(criteria).getSingleResult());
    }
}
