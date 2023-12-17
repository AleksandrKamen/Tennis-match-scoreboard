package matches.repository;

import matches.entity.MatchesEntity;
import matches.entity.MatchesEntity_;
import org.hibernate.Session;
import org.hibernate.query.Query;
import players.entity.PlayersEntity;
import util.HibernateUtil;
import util.repository_util.BaseRepository;
import java.util.List;

public class MathesRepository extends BaseRepository<Integer, MatchesEntity> {
    public MathesRepository() {
        super(MatchesEntity.class);
    }

    public List<MatchesEntity> findMatchesByPlayerId(Integer playerId){
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            var playersEntity = session.find(PlayersEntity.class, playerId);
            var cb = session.getCriteriaBuilder();
            var criteria = cb.createQuery(MatchesEntity.class);
            var matches = criteria.from(MatchesEntity.class);
            criteria.select(matches)
                    .where(cb.or(cb.equal(matches.get(MatchesEntity_.PLAYER1),playersEntity),
                            cb.equal(matches.get(MatchesEntity_.PLAYER2),playersEntity)));
            List<MatchesEntity> resultList = session.createQuery(criteria).getResultList();
            session.getTransaction().commit();
            return resultList;
        }
    }
    public List<MatchesEntity> findMatchesByPlayerId(Integer playerId, Integer page, Integer limit){
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            var playersEntity = session.find(PlayersEntity.class, playerId);
            var cb = session.getCriteriaBuilder();
            var criteria = cb.createQuery(MatchesEntity.class);
            var matches = criteria.from(MatchesEntity.class);
            criteria.select(matches)
                    .where(cb.or(cb.equal(matches.get(MatchesEntity_.PLAYER1),playersEntity),
                            cb.equal(matches.get(MatchesEntity_.PLAYER2),playersEntity)));
            List<MatchesEntity> resultList = session.createQuery(criteria)
                    .setFirstResult(page)
                    .setMaxResults(limit)
                    .getResultList();
            session.getTransaction().commit();
            return resultList;
        }
    }

    public List<MatchesEntity> findMatchesWithPagination(Integer page, Integer limit){
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            var cb = session.getCriteriaBuilder();
            var criteria = cb.createQuery(MatchesEntity.class);
            var matches = criteria.from(MatchesEntity.class);
            criteria.select(matches);
            List<MatchesEntity> resultList = session.createQuery(criteria)
                    .setFirstResult(page)
                    .setMaxResults(limit)
                    .getResultList();
            session.getTransaction().commit();
            return resultList;
        }
    }

}
