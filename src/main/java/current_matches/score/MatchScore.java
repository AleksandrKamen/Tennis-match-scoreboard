package current_matches.score;

import current_matches.score.values.MatchValues;

import java.util.List;

public class MatchScore extends Score<MatchValues> {
    private Integer setsCount = 0;
    private List<String> firstSetResult = List.of("0", "0"), secondSetResult = List.of("0", "0"), thirdSetResult = List.of("0", "0");
    private SetScore setScore = new SetScore();

    @Override
    protected MatchValues getZeroScore() {
        return MatchValues.ZERO;
    }

    @Override
    public MatchState pointWon(int playerNumber) {

        var matchState = setScore.pointWon(playerNumber);

        if (matchState == MatchState.FIRST_PLAYER_WON) {
            return matchWon(0);
        } else if (matchState == MatchState.SECOND_PLAYER_WON) {
            return matchWon(1);
        }
        return MatchState.NOT_OVER;
    }

    public MatchState matchWon(int playerNumber) {
        setsCount++;
        if (setsCount == 1) {
            firstSetResult = setScore.getSetResult();

        } else if (setsCount == 2) {
            secondSetResult = setScore.getSetResult();
        } else {
            thirdSetResult = setScore.getSetResult();
        }
        setScore = new SetScore();
        setPlayerScore(playerNumber, getPlayerScore(playerNumber).getNextValues());
        var playerScore = getPlayerScore(playerNumber);

        if (playerScore == MatchValues.TWO) {
            return playerNumber == 0 ? MatchState.FIRST_PLAYER_WON : MatchState.SECOND_PLAYER_WON;
        } else return MatchState.NOT_OVER;
    }

    public List<String> getCurrentPlayerScore(int playerNumber) {

        return List.of(
                firstSetResult.get(playerNumber),
                secondSetResult.get(playerNumber),
                thirdSetResult.get(playerNumber),
                getPlayerScore().get(playerNumber).getString(),
                setScore.getCurrentPlayerScore(playerNumber).get(1),
                setScore.getCurrentPlayerScore(playerNumber).get(2)
        );
    }
}
