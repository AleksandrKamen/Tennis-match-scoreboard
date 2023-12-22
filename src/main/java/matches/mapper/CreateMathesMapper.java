package matches.mapper;

import matches.dto.CreateMathesDto;
import matches.entity.MatchesEntity;
import players.repository.PlayersRepository;
import util.mapper_util.Mapper;

public class CreateMathesMapper implements Mapper<CreateMathesDto, MatchesEntity> {
   private final PlayersRepository playersRepository = new PlayersRepository();
    @Override
    public MatchesEntity mapFrom(CreateMathesDto object) {

        return MatchesEntity.builder()
                .player1(playersRepository.findByName(object.getPlayer1()).get())
                .player2(playersRepository.findByName(object.getPlayer2()).get())
                .winner(playersRepository.findByName(object.getWinner()).get())
                .build();
    }
}
