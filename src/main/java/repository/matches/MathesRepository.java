package repository.matches;

import entity.matches.MatchesEntity;

import entity.matches.MatchesEntity_;
import entity.players.PlayersEntity;
import jakarta.persistence.EntityManager;
import repository.BaseRepository;

import java.util.List;

public class MathesRepository extends BaseRepository<Integer, MatchesEntity> {
    public MathesRepository(EntityManager entityManager){
        super(MatchesEntity.class, entityManager);
    }
    public List<MatchesEntity> findByPlayerId(Integer playerId){
        var entityManager = getEntityManager();
        var playersEntity = entityManager.find(PlayersEntity.class, playerId);
        var cb = entityManager.getCriteriaBuilder();
        var criteria = cb.createQuery(MatchesEntity.class);
        var matches = criteria.from(MatchesEntity.class);
        criteria.select(matches)
                .where(cb.or(cb.equal(matches.get(MatchesEntity_.PLAYER1),playersEntity),
                cb.equal(matches.get(MatchesEntity_.PLAYER2),playersEntity)));
        return entityManager.createQuery(criteria).getResultList();


    }

}
