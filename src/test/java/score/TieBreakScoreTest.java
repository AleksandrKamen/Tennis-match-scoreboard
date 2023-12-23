package score;

import current_matches.score.MatchState;
import current_matches.score.TieBreakScore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TieBreakScoreTest {
    TieBreakScore tieBreak;

    @BeforeEach
    void init() {
        tieBreak = new TieBreakScore();
    }

    @AfterEach
    void drop() {
        tieBreak = null;
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("return state - game is not over, if score first player < 7 and score second player < 7")
    void gameNotOver(int tieBreakPoint) {
        tieBreak.setPlayerScore(0, tieBreakPoint);
        assertEquals(tieBreak.pointWon(0), MatchState.NOT_OVER);
        tieBreak.setPlayerScore(1, tieBreakPoint);
        assertEquals(tieBreak.pointWon(1), MatchState.NOT_OVER);
    }

    @ParameterizedTest
    @ValueSource(ints = {6, 7, 12, 14})
    @DisplayName("return state - game is not over, if difference between players < 2")
    void gameNotOverAfterSix(int tieBreakPoint) {
        tieBreak.setPlayerScore(0, tieBreakPoint);
        tieBreak.setPlayerScore(1, tieBreakPoint);
        assertEquals(tieBreak.pointWon(0), MatchState.NOT_OVER);
        assertEquals(tieBreak.pointWon(1), MatchState.NOT_OVER);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    @DisplayName("return state - player first or second won, if score 7:x<=5 or x<=5:7")
    void firstOrSecondPlayerWins(int tieBreakPoint) {
        tieBreak.setPlayerScore(0, 6);
        tieBreak.setPlayerScore(1, tieBreakPoint);
        assertEquals(tieBreak.pointWon(0), MatchState.FIRST_PLAYER_WON);

        tieBreak.setPlayerScore(0, tieBreakPoint);
        tieBreak.setPlayerScore(1, 6);
        assertEquals(tieBreak.pointWon(1), MatchState.SECOND_PLAYER_WON);
    }

    @ParameterizedTest
    @ValueSource(ints = {7, 8, 9, 10, 11, 12, 13, 14, 15})
    @DisplayName("return state - player first or second won, if difference between players = 2")
    void firstOrSecondPlayerWinsIfScoreMoreTwo(int tieBreakPoint) {
        tieBreak.setPlayerScore(0, tieBreakPoint + 1);
        tieBreak.setPlayerScore(1, tieBreakPoint);
        assertEquals(tieBreak.pointWon(0), MatchState.FIRST_PLAYER_WON);

        tieBreak.setPlayerScore(0, tieBreakPoint);
        tieBreak.setPlayerScore(1, tieBreakPoint + 1);
        assertEquals(tieBreak.pointWon(1), MatchState.SECOND_PLAYER_WON);
    }
}
