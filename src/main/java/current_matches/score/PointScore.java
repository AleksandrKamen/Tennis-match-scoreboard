package current_matches.score;

import current_matches.score.values.PointValues;

public class PointScore extends Score<PointValues> {
    @Override
    protected PointValues getZeroScore() {
        return PointValues.ZERO;
    }
    @Override
    public MatchState pointWon(int playerNumber) {
        PointValues playerScore = getPlayerScore(playerNumber); // Получаем счет нашего игрока

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
                return playerNumber == 0 ? MatchState.FIRST_PLAYER_WINS: MatchState.SECOND_PLAYER_WINS;
            }
        }
        else if (playerScore.ordinal() == PointValues.ADVANTAGE.ordinal()){
            return playerNumber == 0 ? MatchState.FIRST_PLAYER_WINS: MatchState.SECOND_PLAYER_WINS;
        } else {
            throw new IllegalArgumentException("Cannot call pointWon() on ADVANTAGE");
        }
      return MatchState.NOT_OVER;
    }
}
