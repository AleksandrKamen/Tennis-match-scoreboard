package repository;

import entity.MatchesEntity;
import entity.PlayersEntity;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class MathesRepository extends BaseRepository<Integer, MatchesEntity> {

    public MathesRepository(SessionFactory sessionFactory){
        super(MatchesEntity.class, sessionFactory);
    }
    @Override
    public MatchesEntity save(MatchesEntity entity) {
        return super.save(entity);
    }

    @Override
    public Optional<MatchesEntity> find(Integer id) {
        return super.find(id);
    }

    @Override
    public List<MatchesEntity> findAll() {
        return super.findAll();
    }

    @Override
    public MatchesEntity update(MatchesEntity entity) {
        return super.update(entity);
    }

    @Override
    public boolean delete(Integer id) {
        return super.delete(id);
    }
}
