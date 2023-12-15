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
     var validationResult = createPlayerDtoValidator.isValid(createPlayersDto);
     if (!validationResult.isValid()){
         throw new ValidationException(validationResult.getErrors());
     }
     var playersEntity = createPlayersMapper.mapFrom(createPlayersDto);
     var save = playersRepository.save(playersEntity);
     var readPlayersDto = readPlayersMapper.mapFrom(save);
     return readPlayersDto;
 }
}
