package matches.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateMathesDto {
    private String player1;
    private String player2;
    private String winner;
}
