package matches.repository;

import matches.entity.MatchesEntity;
import org.hibernate.Session;
import util.HibernateUtil;
import util.ChangePlayerNameUtil;
import util.repository_util.BaseRepository;

import java.util.List;

public class MathesRepository extends BaseRepository<Integer, MatchesEntity> {
    public MathesRepository() {
        super(MatchesEntity.class);
    }

    public List<MatchesEntity> findMatchesWithPagination(Integer page, Integer limit) {
        try (var session = HibernateUtil.getSession()) {
            session.beginTransaction();
            var cb = session.getCriteriaBuilder();
            var criteria = cb.createQuery(MatchesEntity.class);
            var matches = criteria.from(MatchesEntity.class);
            criteria.select(matches);
            var resultList = session.createQuery(criteria)
                    .setFirstResult(page)
                    .setMaxResults(limit)
                    .getResultList();
            session.getTransaction().commit();
            return resultList;
        }
    }

    public List<MatchesEntity> findMatchesByPlayerName(String playerName) {
        try (var session = HibernateUtil.getSession()) {
            session.beginTransaction();
            var query = session.createQuery("select m from MatchesEntity m where player1.name like :playerName or player2.name like :playerName", MatchesEntity.class);
            query.setParameter("playerName", ChangePlayerNameUtil.changePlayerNameForSearch(playerName) + "%");
            var resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        }
    }

    public List<MatchesEntity> findMatchesByPlayerNameWithPagination(String playerName, Integer page, Integer limit) {
        try (var session = HibernateUtil.getSession()) {
            session.beginTransaction();
            var query = session.createQuery("select m from MatchesEntity m where player1.name like :playerName or player2.name like :playerName", MatchesEntity.class);
            query.setParameter("playerName", ChangePlayerNameUtil.changePlayerNameForSearch(playerName) + "%");
            query.setFirstResult(page);
            query.setMaxResults(limit);
            var resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        }
    }
}
