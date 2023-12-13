package current_matches.score;

import current_matches.score.values.PointValues;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
    @ParameterizedTest
    @ValueSource(strings = {"ZERO","FIFTEEN","THIRTY","FORTY"})
    @DisplayName("return state - game is not over")
  void gameIsNotOver(String values){
        pointScore.setPlayerScore(0,PointValues.valueOf(values));
        pointScore.setPlayerScore(1,PointValues.valueOf(values));
        assertEquals(pointScore.pointWon(0), MatchState.NOT_OVER);

        pointScore.setPlayerScore(0,PointValues.FORTY);
        pointScore.setPlayerScore(1,PointValues.ADVANTAGE);
        assertEquals(pointScore.pointWon(0), MatchState.NOT_OVER);
  }
    @ParameterizedTest
    @ValueSource(strings = {"ZERO","FIFTEEN","THIRTY"})
    @DisplayName("return state - FIRST_PLAYER_WINS or SECOND_PLAYER_WINS")
    void firstPlayerWins(String values) {
        pointScore.setPlayerScore(0, PointValues.FORTY);
        pointScore.setPlayerScore(1, PointValues.valueOf(values));
        assertEquals(pointScore.pointWon(0), MatchState.FIRST_PLAYER_WINS);

        pointScore.setPlayerScore(0, PointValues.valueOf(values));
        pointScore.setPlayerScore(1, PointValues.FORTY);
        assertEquals(pointScore.pointWon(1), MatchState.SECOND_PLAYER_WINS);

        pointScore.setPlayerScore(0, PointValues.ADVANTAGE);
        pointScore.setPlayerScore(1, PointValues.FORTY);
        assertEquals(pointScore.pointWon(0), MatchState.FIRST_PLAYER_WINS);

        pointScore.setPlayerScore(0, PointValues.FORTY);
        pointScore.setPlayerScore(1, PointValues.ADVANTAGE);
        assertEquals(pointScore.pointWon(1), MatchState.SECOND_PLAYER_WINS);
    }
    @Test
    @DisplayName("throw exception if invalid point name")
    void inv() {
      assertThrows(IllegalArgumentException.class,()-> pointScore.setOppositeScore(0, PointValues.valueOf("someName")));
    }



}