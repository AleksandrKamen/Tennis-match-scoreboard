package matches.mapper;

import matches.dto.ReadMatchesDto;
import matches.entity.MatchesEntity;
import lombok.RequiredArgsConstructor;
import util.mapper_util.Mapper;

@RequiredArgsConstructor
public class ReadMatchesMapper implements Mapper<MatchesEntity, ReadMatchesDto> {
    private static final String TROPHY = "\uD83C\uDFC6";
    @Override
    public ReadMatchesDto mapFrom(MatchesEntity object) {
        String firstPlayerName = object.getPlayer1().equals(object.getWinner())?object.getPlayer1().getName()+ TROPHY :object.getPlayer1().getName();
        String secondPlayerName = object.getPlayer2().equals(object.getWinner())?object.getPlayer2().getName()+ TROPHY :object.getPlayer2().getName();

        return ReadMatchesDto.builder()
                .id(object.getId())
                .player1(firstPlayerName)
                .player2(secondPlayerName)
//                .winner(object.getWinner().getName())
                .build();
    }
}
