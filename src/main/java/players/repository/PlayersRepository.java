package players.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import players.entity.PlayersEntity;
import jakarta.persistence.EntityManager;
import players.entity.PlayersEntity_;
import util.HibernateUtil;
import util.repository_util.BaseRepository;
import java.util.Optional;

public class PlayersRepository extends BaseRepository<Integer, PlayersEntity> {

    public PlayersRepository(){
        super(PlayersEntity.class);
    }

    public Optional<PlayersEntity> findByName(String name){
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            var cb = session.getCriteriaBuilder();
            var criteria = cb.createQuery(PlayersEntity.class);
            var players = criteria.from(PlayersEntity.class);
            criteria.select(players)
                    .where(cb.equal(players
                            .get(PlayersEntity_.NAME),name));
            Optional<PlayersEntity> playersEntity = session.createQuery(criteria).getResultStream().findFirst();
            session.getTransaction().commit();
            return playersEntity;
        }
    }
}
