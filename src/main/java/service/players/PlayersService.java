package service.players;

import dto.players.CreatePlayersDto;
import dto.players.ReadPlayersDto;
import entity.players.PlayersEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import mapper.players.CreatePlayersMapper;
import mapper.players.ReadPlayersMapper;
import repository.players.PlayersRepository;

import java.util.Optional;

@RequiredArgsConstructor
public class PlayersService {
   private final PlayersRepository playersRepository;
   private final ReadPlayersMapper readPlayersMapper;
   private final CreatePlayersMapper createPlayersMapper;

public static PlayersService openService(EntityManager entityManager){
    var playersRepository = new PlayersRepository(entityManager);
    var readPlayersMapper = new ReadPlayersMapper();
    var createPlayersMapper = new CreatePlayersMapper();
    return new PlayersService(playersRepository,readPlayersMapper,createPlayersMapper);
}
 public ReadPlayersDto createPlayer(CreatePlayersDto createPlayersDto){
     var playersEntity = createPlayersMapper.mapFrom(createPlayersDto);
     var save = playersRepository.save(playersEntity);
     var readPlayersDto = readPlayersMapper.mapFrom(save);
     return readPlayersDto;
 }

public Optional<ReadPlayersDto> findPlayerByName(String name){
    return playersRepository.findByName(name)
            .map(readPlayersMapper::mapFrom);
}



}
