package dto.current_matches;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateCurrentMatchesDto {
    private String player1Name;
    private String player2Name;
}
