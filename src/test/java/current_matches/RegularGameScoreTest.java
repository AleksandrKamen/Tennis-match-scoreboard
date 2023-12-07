package current_matches;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RegularGameScoreTest {
     RegularGameScore regularGameScore;
    @BeforeEach
    void init(){
         regularGameScore = new RegularGameScore();
    }
    @AfterEach
    void drop(){
        regularGameScore = null;
    }

    @Test
    @DisplayName("return state - game is not over")
  void gameIsNotOver(){

        assertAll(
                ()-> assertEquals(regularGameScore.getPlayerScore(0),Point.ZERO),
                ()-> assertEquals(regularGameScore.getPlayerScore(1),Point.ZERO),
                () -> assertEquals(regularGameScore.playerWon(0),State.NOT_OVER),
                () -> assertEquals(regularGameScore.playerWon(0), State.NOT_OVER)
        );
        assertAll(
                () -> assertEquals(regularGameScore.playerWon(1),State.NOT_OVER),
                () -> assertEquals(regularGameScore.playerWon(1), State.NOT_OVER),
                ()-> assertEquals(regularGameScore.getPlayerScore(0),Point.THIRTY),
                ()-> assertEquals(regularGameScore.getPlayerScore(1),Point.THIRTY)
        );
               regularGameScore.playerWon(0);
               regularGameScore.playerWon(1);
        assertAll (
            () -> assertEquals(regularGameScore.playerWon(0), State.NOT_OVER),
            () -> assertEquals(regularGameScore.getPlayerScore(0), Point.ADVANTAGE)
        );
  }
    @Test
    @DisplayName("return state - FIRST_PLAYER_WINS, if score 40:x<40")
    void firstPlayerWins() {
        regularGameScore.setPlayerScore(0,Point.FORTY);
        assertEquals(regularGameScore.playerWon(0),State.FIRST_PLAYER_WINS);
    }
    @Test
    @DisplayName("return state - SECOND_PLAYER_WINS, if score x<40:40")
    void secondPlayerWins() {
        regularGameScore.setPlayerScore(1,Point.FORTY);
        assertEquals(regularGameScore.playerWon(1),State.SECOND_PLAYER_WINS);
    }
    @Test
    @DisplayName("return state - FIRST_PLAYER_WINS, if score him score = ADVANTAGE")
    void firstPlayerWinsIfAdvantage() {
        regularGameScore.setPlayerScore(0,Point.ADVANTAGE);
        assertEquals(regularGameScore.playerWon(0),State.FIRST_PLAYER_WINS);
    }
    @Test
    @DisplayName("return state - SECOND_PLAYER_WINS, if score him score = ADVANTAGE")
    void secondPlayerWinsIfAdvantage() {
        regularGameScore.setPlayerScore(1,Point.ADVANTAGE);
        assertEquals(regularGameScore.playerWon(1),State.SECOND_PLAYER_WINS);
    }
    @Test
    @DisplayName("throw exception if invalid point name")
    void inv() {
      assertThrows(IllegalArgumentException.class,()->regularGameScore.setOppositeScore(0,Point.valueOf("someName")));
    }



}