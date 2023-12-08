package matches.mapper;

import matches.dto.CreateMathesDto;
import matches.entity.MatchesEntity;
import lombok.RequiredArgsConstructor;
import util.mapper_util.Mapper;
import players.repository.PlayersRepository;

@RequiredArgsConstructor
public class CreateMathesMapper implements Mapper<CreateMathesDto, MatchesEntity> {
   private final PlayersRepository playersRepository;

    @Override
    public MatchesEntity mapFrom(CreateMathesDto object) {

        return MatchesEntity.builder()
                .player1(playersRepository.findByName(object.getPlayer1()).orElseThrow(IllegalArgumentException::new))
                .player2(playersRepository.findByName(object.getPlayer2()).orElseThrow(IllegalArgumentException::new))
                .winner(playersRepository.findByName(object.getWinner()).orElseThrow(IllegalArgumentException::new))
                .build();
    }
}
