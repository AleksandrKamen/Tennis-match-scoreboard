package current_matches;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    @Nested
    class isGameNotOverTest{
        @ParameterizedTest
        @MethodSource("current_matches.SetScoreTest#getSetPoint")
        @DisplayName("return state - game is not over, if score first player < 5 and score second player < 5")
        void gameIsNotOver(String setPoint) {
            setScore.setPlayerScore(0, SetPoint.valueOf(setPoint));
            assertEquals(setScore.playerWon(0), State.NOT_OVER);
            setScore.setPlayerScore(1, SetPoint.valueOf(setPoint));
            assertEquals(setScore.playerWon(1), State.NOT_OVER);
        }
        @Test
        @DisplayName("return state - game is not over, if score first player = 6 and score second player = 5")
        void gameIsNotOverIfScoreSixFive() {
            setScore.setPlayerScore(0, SetPoint.FIVE);
            setScore.setPlayerScore(1, SetPoint.FIVE);
            assertEquals(setScore.playerWon(0), State.NOT_OVER);
        }
    }
    @Nested
    class FirstOrSecondPlayerWinsTest{
        //1 wins 5:<5
        @ParameterizedTest
        @MethodSource("current_matches.SetScoreTest#getSetPoint")
        @DisplayName("return state: FIRST_PLAYER_WINS, if score first player = 5 and score second player < 5")
        void firstPlayerWins(String setPoint) {
            setScore.setPlayerScore(0, SetPoint.FIVE);
            setScore.setPlayerScore(1, SetPoint.valueOf(setPoint));
            assertEquals(setScore.playerWon(0), State.FIRST_PLAYER_WINS);
        }

        @ParameterizedTest
        @MethodSource("current_matches.SetScoreTest#getSetPoint")
        @DisplayName("return state: SECOND_PLAYER_WINS, if score first player < 5 and score second player = 5")
        void secondPlayerWins(String setPoint) {
            setScore.setPlayerScore(1, SetPoint.FIVE);
            setScore.setPlayerScore(0, SetPoint.valueOf(setPoint));
            assertEquals(setScore.playerWon(1), State.SECOND_PLAYER_WINS);
        }
    }
    @Nested
    class IllegalArgumentTest{
        @Test
        @DisplayName("Throw IllegalArgumentException if setPoint != 0,1,2,3,4,5,6")
        void throwException(){
            setScore.setPlayerScore(0,SetPoint.SEVEN);
            setScore.setPlayerScore(1,SetPoint.SEVEN);
            assertThrows(IllegalArgumentException.class, () -> setScore.playerWon(0));
            assertThrows(IllegalArgumentException.class, () -> setScore.playerWon(1));
        }
    }

        @Nested
    class TieBreakTest{

        @ParameterizedTest
        @ValueSource(ints = {1,2,3,4,5})
        @DisplayName("return state - game is not over, if score first player < 6 and score second player < 6")
        void gameNotOver(int tieBreakPoint){
            var tieBreak = new TieBreakScore();
            tieBreak.setPlayerScore(0,tieBreakPoint);
            assertEquals(setScore.playerWon(0), State.NOT_OVER);
            tieBreak.setPlayerScore(1,tieBreakPoint);
            assertEquals(setScore.playerWon(1), State.NOT_OVER);
        }
        @ParameterizedTest
        @ValueSource(ints = {6,7,12,14})
        @DisplayName("return state - game is not over, if difference between players < 2")
        void gameNotOverAfterSix(int tieBreakPoint){
            var tieBreak = new TieBreakScore();
            tieBreak.setPlayerScore(0,tieBreakPoint);
            tieBreak.setPlayerScore(1,tieBreakPoint);
            assertEquals(setScore.playerWon(0), State.NOT_OVER);
            assertEquals(setScore.playerWon(1), State.NOT_OVER);
        }
        @ParameterizedTest
        @ValueSource(ints = {0,1,2,3,4,5})
        @DisplayName("return state - player first or second wins, if score 6:x<5 or x<5:6")
        void firstOrSecondPlayerWins(int tieBreakPoint){
            var tieBreak = new TieBreakScore();
            tieBreak.setPlayerScore(0,6);
            tieBreak.setPlayerScore(1,tieBreakPoint);
            assertEquals(tieBreak.playerWon(0), State.FIRST_PLAYER_WINS);

            tieBreak.setPlayerScore(0,tieBreakPoint);
            tieBreak.setPlayerScore(1,6);
            assertEquals(tieBreak.playerWon(1), State.SECOND_PLAYER_WINS);
        }
        @ParameterizedTest
        @ValueSource(ints = {7,8,9,10,11,12,13,14,15})
        @DisplayName("return state - player first or second wins, if difference between players = 2")
        void firstOrSecondPlayerWinsIfScoreMereTwo(int tieBreakPoint){
            var tieBreak = new TieBreakScore();
            tieBreak.setPlayerScore(0,tieBreakPoint+1);
            tieBreak.setPlayerScore(1,tieBreakPoint);
            assertEquals(tieBreak.playerWon(0), State.FIRST_PLAYER_WINS);

            tieBreak.setPlayerScore(0,tieBreakPoint);
            tieBreak.setPlayerScore(1,tieBreakPoint+1);
            assertEquals(tieBreak.playerWon(1), State.SECOND_PLAYER_WINS);
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