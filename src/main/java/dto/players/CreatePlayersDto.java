package dto.players;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreatePlayersDto {
    private String name;
}
