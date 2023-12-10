package current_matches.score;

public class TieBreakScore extends Score<Integer>{
    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    public MatchState pointWon(int playerNumber) {
        setPlayerScore(playerNumber,getPlayerScore(playerNumber)+1);
        var playerScore = getPlayerScore(playerNumber); // Получаем счет нашего игрока
        if (playerScore == 7){
            var opposition = getOppositeScore(playerNumber);
            if (opposition <= 5){
                return playerNumber == 0 ? MatchState.FIRST_PLAYER_WINS : MatchState.SECOND_PLAYER_WINS;
            }
        }
        else if (playerScore > 7 && playerScore - getOppositeScore(playerNumber) == 2){
            return playerNumber == 0 ? MatchState.FIRST_PLAYER_WINS : MatchState.SECOND_PLAYER_WINS;
        }
        return MatchState.NOT_OVER;
    }
    public String getCurrentScore(int playerNumber){
        return getPlayerScore()
                .get(playerNumber)
                .toString();
    }
}
