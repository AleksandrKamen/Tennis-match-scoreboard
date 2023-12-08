package current_matches;

import current_matches.score.MatchScore;
import players.dto.CreatePlayersDto;
import lombok.Data;
import java.util.UUID;
@Data
public class CurrentMatches {

    private final UUID uuid;
    private final CreatePlayersDto player1;
    private final CreatePlayersDto player2;
    private CreatePlayersDto winner;
    private MatchScore score;
    public CurrentMatches(UUID uuid, CreatePlayersDto player1, CreatePlayersDto player2) {
        this.uuid = uuid;
        this.player1 = player1;
        this.player2 = player2;
        this.score = new MatchScore();
    }
    public void setWinner(int playerNamber){
        winner = playerNamber == 0?player1:player2;
    }
}
