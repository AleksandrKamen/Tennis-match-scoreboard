package players.service;

import exception.ValidationException;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import players.dto.CreatePlayersDto;
import players.dto.ReadPlayersDto;
import players.mapper.CreatePlayersMapper;
import players.mapper.ReadPlayersMapper;
import players.repository.PlayersRepository;
import validator.Error;
import validator.ValidationResult;
import validator.players.CreatePlayerDtoValidator;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class PlayersService {
   private final PlayersRepository playersRepository;
   private final ReadPlayersMapper readPlayersMapper;
   private final CreatePlayersMapper createPlayersMapper;
   private final CreatePlayerDtoValidator createPlayerDtoValidator;

public static PlayersService openService(EntityManager entityManager){
    var playersRepository = new PlayersRepository(entityManager);
    var readPlayersMapper = new ReadPlayersMapper();
    var createPlayersMapper = new CreatePlayersMapper();
    var createPlayerDtoValidator = new CreatePlayerDtoValidator(entityManager);
    return new PlayersService(playersRepository,readPlayersMapper,createPlayersMapper,createPlayerDtoValidator);
}
 public ReadPlayersDto createPlayer(CreatePlayersDto createPlayersDto){
     ValidationResult validationResult = createPlayerDtoValidator.isValid(createPlayersDto);
     if (!validationResult.isValid()){
         throw new ValidationException(List.of(Error.of(400,"Игрок уже есть в бд")));
     }
     var playersEntity = createPlayersMapper.mapFrom(createPlayersDto);
     var save = playersRepository.save(playersEntity);
     var readPlayersDto = readPlayersMapper.mapFrom(save);
     return readPlayersDto;
 }
    public void createPlayers(List<String> playersNames){
        for (String playersName : playersNames) {
            var playersEntity = createPlayersMapper.mapFrom(
                    CreatePlayersDto.builder()
                            .name(playersName)
                            .build());
            playersRepository.save(playersEntity);
        }
    }
public Optional<ReadPlayersDto> findPlayerByName(String name){
    return playersRepository.findByName(name)
            .map(readPlayersMapper::mapFrom);
}
public List<ReadPlayersDto> findAllPlayers(){
    return playersRepository.findAll().stream()
            .map(readPlayersMapper::mapFrom)
            .toList();
}

}
