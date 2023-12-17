package score;

import current_matches.score.MatchState;
import current_matches.score.SetScore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetScoreTest {
    SetScore setScore;
    @BeforeEach
    void init() {
        setScore = new SetScore();
    }
    @AfterEach
    void drop() {
        setScore = null;
    }
    @Test
    @DisplayName("return state: FIRST_PLAYER_WINS, if score first player = 24")
    void playerWins(){
        for (int i = 0; i <23; i++) {
            assertEquals(setScore.pointWon(0), MatchState.NOT_OVER);
        }
        assertEquals(setScore.pointWon(0), MatchState.FIRST_PLAYER_WON);
    }
}