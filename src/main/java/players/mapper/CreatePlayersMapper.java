package players.mapper;

import players.dto.CreatePlayersDto;
import players.entity.PlayersEntity;
import lombok.RequiredArgsConstructor;
import util.mapper.Mapper;
@RequiredArgsConstructor
public class CreatePlayersMapper implements Mapper<CreatePlayersDto, PlayersEntity> {
    @Override
    public PlayersEntity mapFrom(CreatePlayersDto object) {
        return PlayersEntity.builder()
                .name(object.getName())
                .build();
    }
}
