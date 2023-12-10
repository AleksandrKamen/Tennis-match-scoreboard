package current_matches.score;
import current_matches.score.values.SetValues;
import lombok.Getter;
import java.util.List;

public class SetScore extends Score<SetValues>{
    @Getter
    private List<String> setResult = List.of("0","0");

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
        var matchState = gameScore.pointWon(playerNumber);

        if (matchState == MatchState.FIRST_PLAYER_WINS){
            return setWon(0);
        } else if(matchState == MatchState.SECOND_PLAYER_WINS) {
            return setWon(1);
        }
        return MatchState.NOT_OVER;
    }

    public MatchState setWon(int playerNumber){
        setResult = List.of(gameScore.getCurrentPlayerScore(0).get(0),gameScore.getCurrentPlayerScore(1).get(0));

        setPlayerScore(playerNumber,getPlayerScore(playerNumber).getNextValues());
        this.gameScore = new GameScore();
        return playerNumber == 0 ? MatchState.FIRST_PLAYER_WINS : MatchState.SECOND_PLAYER_WINS;
    }

    public List<String> getCurrentPlayerScore(int playerNumber) {
            return List.of(
                    getPlayerScore().get(playerNumber).getValues(),
                    gameScore.getCurrentPlayerScore(playerNumber).get(0),
                    gameScore.getCurrentPlayerScore(playerNumber).get(1)
            );
    }
}
