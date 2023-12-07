package current_matches;

public class RegularGameScore extends Score<Point> {
    @Override
    protected Point getZeroScore() {
        return Point.ZERO;
    }
    @Override
    public State playerWon(int playerNumber) {
        Point playerScore = getPlayerScore(playerNumber); // Получаем счет нашего игрока

        if (playerScore.ordinal() <= Point.THIRTY.ordinal()) {
            setPlayerScore(playerNumber, playerScore.getNextPoint());
        }
        else if (playerScore.ordinal() == Point.FORTY.ordinal()) {
            var opposition = getOppositeScore(playerNumber).ordinal();

            if (opposition == Point.FORTY.ordinal()){
                setPlayerScore(playerNumber, playerScore.getNextPoint());
            } else if (opposition == Point.ADVANTAGE.ordinal()){
                setOppositeScore(playerNumber,Point.FORTY);
            } else {
                return playerNumber == 0 ? State.FIRST_PLAYER_WINS:State.SECOND_PLAYER_WINS;
            }
        }
        else if (playerScore.ordinal() == Point.ADVANTAGE.ordinal()){
            return playerNumber == 0 ? State.FIRST_PLAYER_WINS:State.SECOND_PLAYER_WINS;
        } else {
            throw new IllegalArgumentException("Cannot call pointWon() on ADVANTAGE");
        }
      return State.NOT_OVER;
    }
}
