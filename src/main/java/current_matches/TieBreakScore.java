package current_matches;

public class TieBreakScore extends Score<Integer>{
    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    public State playerWon(int playerNumber) {
        var playerScore = getPlayerScore(playerNumber); // Получаем счет нашего игрока
        if (playerScore <= 5) {
            setPlayerScore(playerNumber, playerScore + 1);
        }
        else if (playerScore == 6) {
            var opposition = getOppositeScore(playerNumber);
            if (opposition <= 5) {
                return playerNumber == 0 ? State.FIRST_PLAYER_WINS : State.SECOND_PLAYER_WINS;
            } else {
                setPlayerScore(playerNumber, playerScore + 1);
                return State.NOT_OVER;
            }
        }
        else {
            setPlayerScore(playerNumber, playerScore + 1);
            if (++playerScore - getOppositeScore(playerNumber) == 2) {
                return playerNumber == 0 ? State.FIRST_PLAYER_WINS : State.SECOND_PLAYER_WINS;
            }
        }
        return State.NOT_OVER;
    }
}
