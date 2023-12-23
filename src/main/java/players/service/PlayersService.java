package players.service;

import validator.exception.ValidationException;
import players.dto.CreatePlayersDto;
import players.dto.ReadPlayersDto;
import players.mapper.CreatePlayersMapper;
import players.mapper.ReadPlayersMapper;
import players.repository.PlayersRepository;
import validator.players.CreatePlayerDtoValidator;

public class PlayersService {
    private static final PlayersRepository playersRepository = new PlayersRepository();
    private static final ReadPlayersMapper readPlayersMapper = new ReadPlayersMapper();
    private static final CreatePlayersMapper createPlayersMapper = new CreatePlayersMapper();
    private static final CreatePlayerDtoValidator createPlayerDtoValidator = new CreatePlayerDtoValidator();

    public ReadPlayersDto createPlayer(CreatePlayersDto createPlayersDto) {
        var validationResult = createPlayerDtoValidator.isValid(createPlayersDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var playersEntity = createPlayersMapper.mapFrom(createPlayersDto);
        var save = playersRepository.save(playersEntity);
        var readPlayersDto = readPlayersMapper.mapFrom(save);
        return readPlayersDto;
    }
}
