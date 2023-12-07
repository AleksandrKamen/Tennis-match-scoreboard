package matches.repository;

import matches.entity.MatchesEntity;
import matches.entity.MatchesEntity_;
import players.entity.PlayersEntity;
import jakarta.persistence.EntityManager;
import util.repository_util.BaseRepository;

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
    public List<MatchesEntity> findByPlayerId(Integer playerId, int page, int limit){
        var entityManager = getEntityManager();
        var playersEntity = entityManager.find(PlayersEntity.class, playerId);
        var cb = entityManager.getCriteriaBuilder();
        var criteria = cb.createQuery(MatchesEntity.class);
        var matches = criteria.from(MatchesEntity.class);
        criteria.select(matches)
                .where(cb.or(cb.equal(matches.get(MatchesEntity_.PLAYER1),playersEntity),
                cb.equal(matches.get(MatchesEntity_.PLAYER2),playersEntity)));
        return entityManager.createQuery(criteria)
                .setFirstResult(page)
                .setMaxResults(limit)
                .getResultList();
    }

    public List<MatchesEntity> findMatchesWithPagination(int page, int limit){
        var entityManager = getEntityManager();
        var cb = entityManager.getCriteriaBuilder();
        var criteria = cb.createQuery(MatchesEntity.class);
        var matches = criteria.from(MatchesEntity.class);
        criteria.select(matches);
        return entityManager.createQuery(criteria)
                .setFirstResult(page)
                .setMaxResults(limit)
                .getResultList();
    }
}
