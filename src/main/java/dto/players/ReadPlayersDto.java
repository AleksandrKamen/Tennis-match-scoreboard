package dto.players;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ReadPlayersDto {
   private Integer id;
   private  String name;
}