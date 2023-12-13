package current_matches.score;

import current_matches.score.values.GameValues;

import java.util.List;

public class GameScore extends Score<GameValues> {

    private TieBreakScore tieBreak;
    private PointScore pointScore;
    private boolean tieBreakTime = false;
    public GameScore() {
        this.pointScore = new PointScore();
    }
    @Override
    protected GameValues getZeroScore() {
        return GameValues.ZERO;
    }
    @Override
    public MatchState pointWon(int playerNumber) {
        if (tieBreakTime){
            return tieBreak.pointWon(playerNumber);
        }
       var state = pointScore.pointWon(playerNumber);

       if (state == MatchState.FIRST_PLAYER_WINS){
           return gameWon(0);
       } else if(state == MatchState.SECOND_PLAYER_WINS) {
            return gameWon(1);
       }
        return MatchState.NOT_OVER;
    }
    public MatchState gameWon(int playerNumber){
        this.pointScore = new PointScore();
        setPlayerScore(playerNumber,getPlayerScore(playerNumber).getNextValues());
        var playerScore = getPlayerScore(playerNumber).ordinal();

        if (playerScore == GameValues.SIX.ordinal()){
            var opposition = getOppositeScore(playerNumber).ordinal();
            if (opposition == GameValues.SIX.ordinal()){
                tieBreakTime = true;
                tieBreak = new TieBreakScore();
            } else if (opposition <= GameValues.FOUR.ordinal()){
                return playerNumber == 0 ? MatchState.FIRST_PLAYER_WINS : MatchState.SECOND_PLAYER_WINS;
            }
        } else if (playerScore == GameValues.SEVEN.ordinal()){
            return playerNumber == 0 ? MatchState.FIRST_PLAYER_WINS : MatchState.SECOND_PLAYER_WINS;
        }
        return MatchState.NOT_OVER;
    }

    public List<String> getCurrentPlayerScore(int playerNumber){
        if (tieBreakTime){
            return List.of(
                    getPlayerScore().get(playerNumber).getValue() + "/"+ tieBreak.getCurrentScore(playerNumber),
                    pointScore.getCurrentPlayerScore(playerNumber)
            );
        }
        return List.of(
                getPlayerScore().get(playerNumber).getValue(),
                pointScore.getCurrentPlayerScore(playerNumber)
        );
    }

}
