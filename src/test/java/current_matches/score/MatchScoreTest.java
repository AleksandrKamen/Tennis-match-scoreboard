package current_matches.score;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchScoreTest {

    MatchScore matchScore;
    @BeforeEach
    void init() {
        matchScore = new MatchScore();
    }
    @AfterEach
    void drop() {
        matchScore = null;
    }

    @Test
    @DisplayName("return state: FIRST_PLAYER_WINS, if score first player = 48")
    void playerWins(){
        for (int i = 0; i <47; i++) {
            assertEquals(matchScore.playerWon(0), MatchState.NOT_OVER);
        }
        assertEquals(matchScore.playerWon(0), MatchState.FIRST_PLAYER_WINS);
    }


}