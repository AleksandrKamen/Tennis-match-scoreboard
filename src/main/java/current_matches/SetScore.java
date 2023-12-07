package current_matches;

public class SetScore extends Score<SetPoint> {
    TieBreakScore tieBreak;

    @Override
    protected SetPoint getZeroScore() {
        return SetPoint.ZERO;
    }

    @Override
    public State playerWon(int playerNumber) {
        var playerScore = getPlayerScore(playerNumber); // Получаем счет нашего игрока

        if (playerScore.ordinal() <= SetPoint.FOUR.ordinal()) {
            setPlayerScore(playerNumber, playerScore.getNextSetPoint());
        } else if (playerScore.ordinal() == SetPoint.FIVE.ordinal()) {
            var opposition = getOppositeScore(playerNumber).ordinal();

            if (opposition == SetPoint.FIVE.ordinal()) {
                setPlayerScore(playerNumber, playerScore.getNextSetPoint());
            } else if (opposition == SetPoint.SIX.ordinal()) {
                tieBreak = new TieBreakScore();
                return tieBreak.playerWon(playerNumber);

            } else if (opposition <= SetPoint.FOUR.ordinal()) {
                return playerNumber == 0 ? State.FIRST_PLAYER_WINS : State.SECOND_PLAYER_WINS;
            } else {
                throw new IllegalArgumentException("SetPoint name");
            }
        } else if (playerScore.ordinal() == SetPoint.SIX.ordinal()) {
            setPlayerScore(playerNumber, playerScore.getNextSetPoint());
            return playerNumber == 0 ? State.FIRST_PLAYER_WINS : State.SECOND_PLAYER_WINS;
        } else {
            throw new IllegalArgumentException("Cannot call pointWon()");
        }
        return State.NOT_OVER;
    }
}
