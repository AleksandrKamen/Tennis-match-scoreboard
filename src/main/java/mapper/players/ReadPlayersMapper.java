package mapper.players;

import dto.players.ReadPlayersDto;
import entity.PlayersEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import mapper.Mapper;

@RequiredArgsConstructor
public class ReadPlayersMapper implements Mapper<PlayersEntity, ReadPlayersDto> {
    @Override
    public ReadPlayersDto mapFrom(PlayersEntity object) {
        return ReadPlayersDto.builder()
                .id(object.getId())
                .name(object.getName())
                .build();
    }
}
