package current_matches.score;

import current_matches.score.values.PointValues;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegularGameScoreTest {
     PointScore pointScore;
    @BeforeEach
    void init(){
         pointScore = new PointScore();
    }
    @AfterEach
    void drop(){
        pointScore = null;
    }


    @Test
    void pointWon(){
        for (int i = 0; i <3; i++) {
            assertEquals(pointScore.pointWon(0), MatchState.NOT_OVER);
        }
        assertEquals(pointScore.pointWon(0), MatchState.FIRST_PLAYER_WINS);
    }

    @Test
    @DisplayName("return state - game is not over")
  void gameIsNotOver(){

        assertAll(
                ()-> assertEquals(pointScore.getPlayerScore(0), PointValues.ZERO),
                ()-> assertEquals(pointScore.getPlayerScore(1), PointValues.ZERO),
                () -> assertEquals(pointScore.pointWon(0), MatchState.NOT_OVER),
                () -> assertEquals(pointScore.pointWon(0), MatchState.NOT_OVER)
        );
        assertAll(
                () -> assertEquals(pointScore.pointWon(1), MatchState.NOT_OVER),
                () -> assertEquals(pointScore.pointWon(1), MatchState.NOT_OVER),
                ()-> assertEquals(pointScore.getPlayerScore(0), PointValues.THIRTY),
                ()-> assertEquals(pointScore.getPlayerScore(1), PointValues.THIRTY)
        );
               pointScore.pointWon(0);
               pointScore.pointWon(1);
        assertAll (
            () -> assertEquals(pointScore.pointWon(0), MatchState.NOT_OVER),
            () -> assertEquals(pointScore.getPlayerScore(0), PointValues.ADVANTAGE)
        );
  }
    @Test
    @DisplayName("return state - FIRST_PLAYER_WINS, if score 40:x<40")
    void firstPlayerWins() {
        pointScore.setPlayerScore(0, PointValues.FORTY);
        assertEquals(pointScore.pointWon(0), MatchState.FIRST_PLAYER_WINS);
    }
    @Test
    @DisplayName("return state - SECOND_PLAYER_WINS, if score x<40:40")
    void secondPlayerWins() {
        pointScore.setPlayerScore(1, PointValues.FORTY);
        assertEquals(pointScore.pointWon(1), MatchState.SECOND_PLAYER_WINS);
    }
    @Test
    @DisplayName("return state - FIRST_PLAYER_WINS, if him score = ADVANTAGE")
    void firstPlayerWinsIfAdvantage() {
        pointScore.setPlayerScore(0, PointValues.ADVANTAGE);
        assertEquals(pointScore.pointWon(0), MatchState.FIRST_PLAYER_WINS);
    }
    @Test
    @DisplayName("return state - SECOND_PLAYER_WINS, if him score = ADVANTAGE")
    void secondPlayerWinsIfAdvantage() {
        pointScore.setPlayerScore(1, PointValues.ADVANTAGE);
        assertEquals(pointScore.pointWon(1), MatchState.SECOND_PLAYER_WINS);
    }
    @Test
    @DisplayName("throw exception if invalid point name")
    void inv() {
      assertThrows(IllegalArgumentException.class,()-> pointScore.setOppositeScore(0, PointValues.valueOf("someName")));
    }



}