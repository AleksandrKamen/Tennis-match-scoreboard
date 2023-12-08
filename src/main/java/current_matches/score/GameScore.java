package current_matches.score;

import current_matches.score.values.GameValues;

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
    public MatchState playerWon(int playerNumber) {
        if (tieBreakTime){
            return tieBreak.playerWon(playerNumber);
        }

       var state = pointScore.playerWon(playerNumber);

       if (state == MatchState.FIRST_PLAYER_WINS){
           return gameWon(0);
       } else if(state == MatchState.SECOND_PLAYER_WINS) {
            return gameWon(1);
       }
        return MatchState.NOT_OVER;
    }
    public MatchState gameWon(int playerNumber){
        this.pointScore = new PointScore(); // очищаем point
        setPlayerScore(playerNumber,getPlayerScore(playerNumber).getNextValues()); // обновляем счет по геймам
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
}
