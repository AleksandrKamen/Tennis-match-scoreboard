package mapper.matches;

import dto.matches.ReadMatchesDto;
import entity.matches.MatchesEntity;
import lombok.RequiredArgsConstructor;
import mapper.Mapper;

@RequiredArgsConstructor
public class ReadMatchesMapper implements Mapper<MatchesEntity, ReadMatchesDto> {
    @Override
    public ReadMatchesDto mapFrom(MatchesEntity object) {
        return ReadMatchesDto.builder()
                .id(object.getId())
                .player1(object.getPlayer1().getName())
                .player2(object.getPlayer2().getName())
                .winner(object.getWinner().getName())
                .build();
    }
}
