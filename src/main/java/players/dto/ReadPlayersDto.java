package players.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ReadPlayersDto {
   private Integer id;
   private  String name;
}
