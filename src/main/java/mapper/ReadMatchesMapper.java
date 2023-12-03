package mapper;

import dto.matches.ReadMatchesDto;
import entity.MatchesEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadMatchesMapper implements Mapper<MatchesEntity, ReadMatchesDto> {
    private static final ReadMatchesMapper INSTANCE = new ReadMatchesMapper();
    public ReadMatchesMapper getInstance(){return INSTANCE;}


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
