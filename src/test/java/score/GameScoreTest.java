package score;

import current_matches.score.GameScore;
import current_matches.score.MatchState;
import current_matches.score.values.GameValues;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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
    class isGameNotOverTest {
        @ParameterizedTest
        @MethodSource("score.GameScoreTest#getSetPoint")
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
    class FirstOrSecondPlayerWinsTest {
        @ParameterizedTest
        @MethodSource("score.GameScoreTest#getSetPoint")
        @DisplayName("return state: FIRST_PLAYER_WON, if score first player = 5 and score second player < 5")
        void firstPlayerWins(String setPoint) {
            gameScore.setPlayerScore(0, GameValues.FIVE);
            gameScore.setPlayerScore(1, GameValues.valueOf(setPoint));
            assertEquals(gameScore.gameWon(0), MatchState.FIRST_PLAYER_WON);
        }

        @ParameterizedTest
        @MethodSource("score.GameScoreTest#getSetPoint")
        @DisplayName("return state: SECOND_PLAYER_WON, if score first player < 5 and score second player = 5")
        void secondPlayerWins(String setPoint) {
            gameScore.setPlayerScore(1, GameValues.FIVE);
            gameScore.setPlayerScore(0, GameValues.valueOf(setPoint));
            assertEquals(gameScore.gameWon(1), MatchState.SECOND_PLAYER_WON);
        }
    }

    @Test
    void startTieBreak() {
        gameScore.setPlayerScore(0, GameValues.FIVE);
        gameScore.setPlayerScore(1, GameValues.SIX);
        assertEquals(gameScore.gameWon(0), MatchState.NOT_OVER);
        for (int i = 0; i < 6; i++) {
            assertEquals(gameScore.pointWon(0), MatchState.NOT_OVER);
        }
        assertEquals(gameScore.pointWon(0), MatchState.FIRST_PLAYER_WON);
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