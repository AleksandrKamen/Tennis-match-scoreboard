package current_matches.score;

import current_matches.score.values.GameValues;

import java.util.List;

public class GameScore extends Score<GameValues> {

    private TieBreakScore tieBreak;
    private PointScore pointScore;
    private boolean isTieBreakTime = false;

    public GameScore() {
        this.pointScore = new PointScore();
    }

    @Override
    protected GameValues getZeroScore() {
        return GameValues.ZERO;
    }

    @Override
    public MatchState pointWon(int playerNumber) {
        if (isTieBreakTime) {
            return tieBreak.pointWon(playerNumber);
        }
        var state = pointScore.pointWon(playerNumber);

        if (state == MatchState.FIRST_PLAYER_WON) {
            return gameWon(0);
        } else if (state == MatchState.SECOND_PLAYER_WON) {
            return gameWon(1);
        }
        return MatchState.NOT_OVER;
    }

    public MatchState gameWon(int playerNumber) {
        pointScore = new PointScore();
        setPlayerScore(playerNumber, getPlayerScore(playerNumber).getNextValues());
        var playerScore = getPlayerScore(playerNumber).ordinal();

        if (playerScore == GameValues.SIX.ordinal()) {
            var oppositionScore = getOppositeScore(playerNumber).ordinal();
            if (oppositionScore == GameValues.SIX.ordinal()) {
                isTieBreakTime = true;
                tieBreak = new TieBreakScore();
            } else if (oppositionScore <= GameValues.FOUR.ordinal()) {
                return playerNumber == 0 ? MatchState.FIRST_PLAYER_WON : MatchState.SECOND_PLAYER_WON;
            }
        } else if (playerScore == GameValues.SEVEN.ordinal()) {
            return playerNumber == 0 ? MatchState.FIRST_PLAYER_WON : MatchState.SECOND_PLAYER_WON;
        }
        return MatchState.NOT_OVER;
    }

    public List<String> getCurrentPlayerScore(int playerNumber) {
        if (isTieBreakTime) {
            return List.of(
                    getPlayerScore().get(playerNumber).getString() + "/" + tieBreak.getCurrentScore(playerNumber),
                    pointScore.getCurrentPlayerScore(playerNumber)
            );
        }
        return List.of(
                getPlayerScore().get(playerNumber).getString(),
                pointScore.getCurrentPlayerScore(playerNumber)
        );
    }
}
