package mapper.players;

import dto.players.ReadPlayersDto;
import entity.PlayersEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mapper.Mapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadPlayersMapper implements Mapper<PlayersEntity, ReadPlayersDto> {
    private static final ReadPlayersMapper INSTANCE = new ReadPlayersMapper();
    public static ReadPlayersMapper getInstance(){return INSTANCE;}
    @Override
    public ReadPlayersDto mapFrom(PlayersEntity object) {
        return ReadPlayersDto.builder()
                .id(object.getId())
                .name(object.getName())
                .build();
    }
}
