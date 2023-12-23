package score;

import current_matches.score.MatchScore;
import current_matches.score.MatchState;
import org.junit.jupiter.api.*;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
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
    @DisplayName("return state: FIRST_PLAYER_WINS, if sets score = 2:0")
    void playerWins() {
        for (int i = 0; i < 47; i++) {
            assertEquals(matchScore.pointWon(0), MatchState.NOT_OVER);
        }
        assertEquals(matchScore.pointWon(0), MatchState.FIRST_PLAYER_WON);
    }

    @Test
    @DisplayName("return state: NOT_OVER, if sets score first 1:1")
    void gameNotOverIfScoreTwoTwo() {
        for (int i = 0; i < 24; i++) {
            assertEquals(matchScore.pointWon(0), MatchState.NOT_OVER);
        }
        for (int i = 0; i < 24; i++) {
            assertEquals(matchScore.pointWon(1), MatchState.NOT_OVER);
        }
        assertEquals(matchScore.pointWon(0), MatchState.NOT_OVER);
    }

    @Test
    @DisplayName("return state: FIRST_PLAYER_WINS, if sets score first 2:1")
    void playerWonIfScoreThreeOne() {
        for (int i = 0; i < 24; i++) {
            assertEquals(matchScore.pointWon(0), MatchState.NOT_OVER);
        }
        for (int i = 0; i < 24; i++) {
            assertEquals(matchScore.pointWon(1), MatchState.NOT_OVER);
        }
        for (int i = 0; i < 23; i++) {
            assertEquals(matchScore.pointWon(0), MatchState.NOT_OVER);
        }
        assertEquals(matchScore.pointWon(0), MatchState.FIRST_PLAYER_WON);
    }

    @Nested
    class CurrentScore {
        @Test
        @DisplayName("return score: 0 0 0 0 0 0 at the beginning of the game")
        void beginningGame() {
            assertThat(matchScore.getCurrentPlayerScore(0)).isEqualTo(List.of("0", "0", "0", "0", "0", "0"));
            assertThat(matchScore.getCurrentPlayerScore(1)).isEqualTo(List.of("0", "0", "0", "0", "0", "0"));

        }

        @Test
        @DisplayName("return score first player: 6 0 0 1 0 0 if first player won set")
        void firstPlayerWonSet() {
            for (int i = 0; i < 24; i++) {
                matchScore.pointWon(0);
            }
            assertThat(matchScore.getCurrentPlayerScore(0)).isEqualTo(List.of("6", "0", "0", "1", "0", "0"));
            assertThat(matchScore.getCurrentPlayerScore(1)).isEqualTo(List.of("0", "0", "0", "0", "0", "0"));
        }

        @Test
        @DisplayName("return score first player: 6 0 0 1 0 0 and score second player: 0 6 0 1 0 0")
        void scoreSetsTwoTwo() {
            for (int i = 0; i < 24; i++) {
                matchScore.pointWon(0);
            }
            for (int i = 0; i < 24; i++) {
                matchScore.pointWon(1);
            }
            assertThat(matchScore.getCurrentPlayerScore(0)).isEqualTo(List.of("6", "0", "0", "1", "0", "0"));
            assertThat(matchScore.getCurrentPlayerScore(1)).isEqualTo(List.of("0", "6", "0", "1", "0", "0"));
        }

        @Test
        @DisplayName("return score first player: 6/0 0 0 1 0 0 and score second player: 6/7 0 0 0 0 0 if tiebrak in first set")
        void TieBreak() {
            for (int i = 0; i < 20; i++) {
                matchScore.pointWon(0);
            }
            for (int i = 0; i < 20; i++) {
                matchScore.pointWon(1);
            }
            for (int i = 0; i < 4; i++) {
                matchScore.pointWon(0);
            }
            for (int i = 0; i < 11; i++) {
                matchScore.pointWon(1);
            }

            assertThat(matchScore.getCurrentPlayerScore(0)).isEqualTo(List.of("6/0", "0", "0", "0", "0", "0"));
            assertThat(matchScore.getCurrentPlayerScore(1)).isEqualTo(List.of("6/7", "0", "0", "1", "0", "0"));
        }


    }


}