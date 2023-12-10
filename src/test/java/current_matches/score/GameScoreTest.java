package current_matches.score;

import current_matches.score.values.GameValues;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
class GameScoreTest {
    GameScore gameScore;
    @BeforeEach
    void init() {
        gameScore = new GameScore();
    }
    @AfterEach
    void drop() {
        gameScore = null;
    }
    @Nested
    class isGameNotOverTest{
        @ParameterizedTest
        @MethodSource("current_matches.score.GameScoreTest#getSetPoint")
        @DisplayName("return state - game is not over, if score first player < 5 and score second player < 5")
        void gameIsNotOver(String setPoint) {
            gameScore.setPlayerScore(0, GameValues.valueOf(setPoint));
            assertEquals(gameScore.pointWon(0), MatchState.NOT_OVER);
            gameScore.setPlayerScore(1, GameValues.valueOf(setPoint));
            assertEquals(gameScore.pointWon(1), MatchState.NOT_OVER);
        }
        @Test
        @DisplayName("return state - game is not over, if score first player = 6 and score second player = 5")
        void gameIsNotOverIfScoreSixFive() {
            gameScore.setPlayerScore(0, GameValues.FIVE);
            gameScore.setPlayerScore(1, GameValues.FIVE);
            assertEquals(gameScore.pointWon(0), MatchState.NOT_OVER);
        }
    }
    @Nested
    class FirstOrSecondPlayerWinsTest{
        //1 wins 5:<5
        @ParameterizedTest
        @MethodSource("current_matches.score.GameScoreTest#getSetPoint")
        @DisplayName("return state: FIRST_PLAYER_WINS, if score first player = 5 and score second player < 5")
        void firstPlayerWins(String setPoint) {
            gameScore.setPlayerScore(0, GameValues.FIVE);
            gameScore.setPlayerScore(1, GameValues.valueOf(setPoint));
            assertEquals(gameScore.gameWon(0), MatchState.FIRST_PLAYER_WINS);
        }

        @ParameterizedTest
        @MethodSource("current_matches.score.GameScoreTest#getSetPoint")
        @DisplayName("return state: SECOND_PLAYER_WINS, if score first player < 5 and score second player = 5")
        void secondPlayerWins(String setPoint) {
            gameScore.setPlayerScore(1, GameValues.FIVE);
            gameScore.setPlayerScore(0, GameValues.valueOf(setPoint));
            assertEquals(gameScore.gameWon(1), MatchState.SECOND_PLAYER_WINS);
        }
    }
        @Nested
    class TieBreakTest{
        @Test
        void startTieBreak(){
            gameScore.setPlayerScore(0, GameValues.FIVE);
            gameScore.setPlayerScore(1, GameValues.SIX);
            assertEquals(gameScore.gameWon(0), MatchState.NOT_OVER);

            for (int i = 0; i <6; i++) {
                assertEquals(gameScore.pointWon(0), MatchState.NOT_OVER);
            }
            assertEquals(gameScore.pointWon(0), MatchState.FIRST_PLAYER_WINS);

        }
        @ParameterizedTest
        @ValueSource(ints = {1,2,3,4,5,6})
        @DisplayName("return state - game is not over, if score first player < 7 and score second player < 7")
        void gameNotOver(int tieBreakPoint){
            var tieBreak = new TieBreakScore();
            tieBreak.setPlayerScore(0,tieBreakPoint);
            assertEquals(gameScore.pointWon(0), MatchState.NOT_OVER);
            tieBreak.setPlayerScore(1,tieBreakPoint);
            assertEquals(gameScore.pointWon(1), MatchState.NOT_OVER);
        }
        @ParameterizedTest
        @ValueSource(ints = {6,7,12,14})
        @DisplayName("return state - game is not over, if difference between players < 2")
        void gameNotOverAfterSix(int tieBreakPoint){
            var tieBreak = new TieBreakScore();
            tieBreak.setPlayerScore(0,tieBreakPoint);
            tieBreak.setPlayerScore(1,tieBreakPoint);
            assertEquals(gameScore.pointWon(0), MatchState.NOT_OVER);
            assertEquals(gameScore.pointWon(1), MatchState.NOT_OVER);
        }
        @ParameterizedTest
        @ValueSource(ints = {0,1,2,3,4,5})
        @DisplayName("return state - player first or second wins, if score 7:x<=5 or x<=5:7")
        void firstOrSecondPlayerWins(int tieBreakPoint){
            var tieBreak = new TieBreakScore();
            tieBreak.setPlayerScore(0,6);
            tieBreak.setPlayerScore(1,tieBreakPoint);
            assertEquals(tieBreak.pointWon(0), MatchState.FIRST_PLAYER_WINS);

            tieBreak.setPlayerScore(0,tieBreakPoint);
            tieBreak.setPlayerScore(1,6);
            assertEquals(tieBreak.pointWon(1), MatchState.SECOND_PLAYER_WINS);
        }
        @ParameterizedTest
        @ValueSource(ints = {7,8,9,10,11,12,13,14,15})
        @DisplayName("return state - player first or second wins, if difference between players = 2")
        void firstOrSecondPlayerWinsIfScoreMoreTwo(int tieBreakPoint){
            var tieBreak = new TieBreakScore();
            tieBreak.setPlayerScore(0,tieBreakPoint+1);
            tieBreak.setPlayerScore(1,tieBreakPoint);
            assertEquals(tieBreak.pointWon(0), MatchState.FIRST_PLAYER_WINS);

            tieBreak.setPlayerScore(0,tieBreakPoint);
            tieBreak.setPlayerScore(1,tieBreakPoint+1);
            assertEquals(tieBreak.pointWon(1), MatchState.SECOND_PLAYER_WINS);
        }
    }

    static Stream<Arguments> getSetPoint() {
        return Stream.of(
                Arguments.of("ZERO"),
                Arguments.of("ONE"),
                Arguments.of("TWO"),
                Arguments.of("THREE"),
                Arguments.of("FOUR")
        );
    }

}