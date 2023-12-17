package current_matches.score;

import current_matches.score.values.PointValues;

public class PointScore extends Score<PointValues> {
    @Override
    protected PointValues getZeroScore() {
        return PointValues.ZERO;
    }
    @Override
    public MatchState pointWon(int playerNumber) {
        var playerScore = getPlayerScore(playerNumber);

        if (playerScore.ordinal() <= PointValues.THIRTY.ordinal()) {
            setPlayerScore(playerNumber, playerScore.getNextValues());
        }
        else if (playerScore.ordinal() == PointValues.FORTY.ordinal()) {
            var opposition = getOppositeScore(playerNumber).ordinal();

            if (opposition == PointValues.FORTY.ordinal()){
                setPlayerScore(playerNumber, playerScore.getNextValues());
            } else if (opposition == PointValues.ADVANTAGE.ordinal()){
                setOppositeScore(playerNumber, PointValues.FORTY);
            } else {
                return playerNumber == 0 ? MatchState.FIRST_PLAYER_WON : MatchState.SECOND_PLAYER_WON;
            }
        }
        else if (playerScore.ordinal() == PointValues.ADVANTAGE.ordinal()){
            return playerNumber == 0 ? MatchState.FIRST_PLAYER_WON : MatchState.SECOND_PLAYER_WON;
        } else {
            throw new IllegalArgumentException("Cannot call pointWon() on ADVANTAGE");
        }
      return MatchState.NOT_OVER;
    }

    public String getCurrentPlayerScore(int playerNumber){
        return getPlayerScore().get(playerNumber).getValue();
    }
}
