package players.mapper;

import players.dto.ReadPlayersDto;
import players.entity.PlayersEntity;
import lombok.RequiredArgsConstructor;
import util.mapper_util.Mapper;

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
