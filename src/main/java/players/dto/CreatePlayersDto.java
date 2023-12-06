package players.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreatePlayersDto {
    private String name;
}
