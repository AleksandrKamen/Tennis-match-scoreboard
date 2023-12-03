package mapper;

import dto.ReadPlayersDto;
import entity.PlayersEntity;

public class ReadPlayersMapper implements Mapper<PlayersEntity, ReadPlayersDto>{
    @Override
    public ReadPlayersDto mapFrom(PlayersEntity object) {
        return ReadPlayersDto.builder()
                .id(object.getId())
                .name(object.getName())
                .build();
    }
}
