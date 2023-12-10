package current_matches.score;

import current_matches.score.values.MatchValues;

import java.util.List;

public class MatchScore extends Score<MatchValues> { // Класс описывающий счет матча
    private Integer setCount = 0;
    private List<String> firstSetResult = List.of("0","0");
    private List<String> secondSetResult = List.of("0","0");
    private List<String> thirdSetResult = List.of("0","0");
    private SetScore setScore = new SetScore();
    @Override
    protected MatchValues getZeroScore() {
        return MatchValues.ZERO;
    }
    @Override
    public MatchState pointWon(int playerNumber) {

        var matchState = setScore.pointWon(playerNumber);

        if (matchState == MatchState.FIRST_PLAYER_WINS){
            return matchWon(0);
        } else if(matchState == MatchState.SECOND_PLAYER_WINS) {
            return matchWon(1);
        }
        return MatchState.NOT_OVER;
    }
    public MatchState matchWon(int playerNumber){
        setCount++;
        if (setCount == 1){
            firstSetResult = setScore.getSetResult();

        } else if (setCount == 2){
            secondSetResult = setScore.getSetResult();
        } else {
            thirdSetResult = setScore.getSetResult();
        }

        setScore = new SetScore();
        setPlayerScore(playerNumber,getPlayerScore(playerNumber).getNextValues());
        var playerScore = getPlayerScore(playerNumber);

        if (playerScore == MatchValues.TWO){
            return playerNumber == 0 ? MatchState.FIRST_PLAYER_WINS : MatchState.SECOND_PLAYER_WINS;
        } else return MatchState.NOT_OVER;
    }
    public List<String> getCurrentPlayerScore(int playerNumber){

            return List.of(
                    firstSetResult.get(playerNumber),
                    secondSetResult.get(playerNumber),
                    thirdSetResult.get(playerNumber),
                    getPlayerScore().get(playerNumber).getValue(),
                    setScore.getCurrentPlayerScore(playerNumber).get(1),
                    setScore.getCurrentPlayerScore(playerNumber).get(2)
            );
    }
}
