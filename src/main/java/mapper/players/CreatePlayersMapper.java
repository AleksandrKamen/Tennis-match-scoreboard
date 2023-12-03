package mapper.players;

import dto.players.CreatePlayersDto;
import entity.PlayersEntity;
import mapper.Mapper;

public class CreatePlayersMapper implements Mapper<CreatePlayersDto, PlayersEntity> {
    @Override
    public PlayersEntity mapFrom(CreatePlayersDto object) {
        return PlayersEntity.builder()
                .name(object.getName())
                .build();
    }
}
