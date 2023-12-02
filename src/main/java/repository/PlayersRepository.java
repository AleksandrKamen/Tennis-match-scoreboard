package repository;

import entity.PlayersEntity;
import org.hibernate.SessionFactory;
import java.util.List;
import java.util.Optional;

public class PlayersRepository extends BaseRepository<Integer, PlayersEntity>{

    public PlayersRepository(SessionFactory sessionFactory){
        super(PlayersEntity.class, sessionFactory);
    }
    @Override
    public PlayersEntity save(PlayersEntity entity) {
        return super.save(entity);
    }
    @Override
    public Optional<PlayersEntity> find(Integer id) {
        return super.find(id);
    }
    @Override
    public List<PlayersEntity> findAll() {
        return super.findAll();
    }
    @Override
    public PlayersEntity update(PlayersEntity entity) {
        return super.update(entity);
    }
    @Override
    public boolean delete(Integer id) {
        return super.delete(id);
    }
}
