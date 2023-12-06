package current_matches;

import players.dto.CreatePlayersDto;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CurrentMatches {

    private final UUID uuid;
    private final CreatePlayersDto player1;
    private final CreatePlayersDto player2;
    private final MatchScore score;
    public CurrentMatches(UUID uuid, CreatePlayersDto player1, CreatePlayersDto player2, MatchScore score) {
        this.uuid = uuid;
        this.player1 = player1;
        this.player2 = player2;
        this.score = new MatchScore();
    }
}
