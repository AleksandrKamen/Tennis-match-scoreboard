package matches.mapper;

import matches.dto.ReadMatchesDto;
import matches.entity.MatchesEntity;
import util.mapper_util.Mapper;
public class ReadMatchesMapper implements Mapper<MatchesEntity, ReadMatchesDto> {
    private static final String TROPHY = "\uD83C\uDFC6";
    @Override
    public ReadMatchesDto mapFrom(MatchesEntity object) {
        var firstPlayerName = object.getPlayer1().getName();
        var secondPlayerName = object.getPlayer2().getName();

        if (firstPlayerName.equals(object.getWinner().getName())){
            firstPlayerName += TROPHY;
        } else {secondPlayerName += TROPHY;}

        return ReadMatchesDto.builder()
                .id(object.getId())
                .player1(firstPlayerName)
                .player2(secondPlayerName)
                .build();
    }
}
