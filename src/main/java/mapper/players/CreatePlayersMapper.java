package mapper.players;

import dto.players.CreatePlayersDto;
import entity.players.PlayersEntity;
import lombok.RequiredArgsConstructor;
import mapper.Mapper;
@RequiredArgsConstructor
public class CreatePlayersMapper implements Mapper<CreatePlayersDto, PlayersEntity> {
    @Override
    public PlayersEntity mapFrom(CreatePlayersDto object) {
        return PlayersEntity.builder()
                .name(object.getName())
                .build();
    }
}
