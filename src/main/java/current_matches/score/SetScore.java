package current_matches.score;

import current_matches.score.values.SetValues;

public class SetScore extends Score<SetValues>{
   private GameScore gameScore;

    public SetScore() {
        this.gameScore = new GameScore();
    }

    @Override
    protected SetValues getZeroScore() {
        return SetValues.ZERO;
    }

    @Override
    public MatchState pointWon(int playerNumber) {
        MatchState state = gameScore.pointWon(playerNumber);

        if (state == MatchState.FIRST_PLAYER_WINS){
            return setWon(0);
        } else if(state == MatchState.SECOND_PLAYER_WINS) {
            return setWon(1);
        }
        return MatchState.NOT_OVER;
    }

    public MatchState setWon(int playerNumber){
        this.gameScore = new GameScore();
        return playerNumber == 0 ? MatchState.FIRST_PLAYER_WINS : MatchState.SECOND_PLAYER_WINS;
    }
}
