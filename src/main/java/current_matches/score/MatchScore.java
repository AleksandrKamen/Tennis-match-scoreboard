package current_matches.score;

import current_matches.score.values.MatchValues;

import java.util.List;

public class MatchScore extends Score<MatchValues> { // Класс описывающий счет матча
  private SetScore setScore = new SetScore();
    @Override
    protected MatchValues getZeroScore() {
        return MatchValues.ZERO;
    }
    @Override
    public MatchState pointWon(int playerNumber) {
        MatchState state = setScore.pointWon(playerNumber);

        if (state == MatchState.FIRST_PLAYER_WINS){
            return matchWon(0);
        } else if(state == MatchState.SECOND_PLAYER_WINS) {
            return matchWon(1);
        }
        return MatchState.NOT_OVER;
    }
    public MatchState matchWon(int playerNumber){
        setScore = new SetScore();
        setPlayerScore(playerNumber,getPlayerScore(playerNumber).getNextValues());
        var playerScore = getPlayerScore(playerNumber);

        if (playerScore == MatchValues.TWO){
            return playerNumber == 0 ? MatchState.FIRST_PLAYER_WINS : MatchState.SECOND_PLAYER_WINS;
        } else return MatchState.NOT_OVER;

    }

}
