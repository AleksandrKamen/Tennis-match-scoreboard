package current_matches.score;

public class TieBreakScore extends Score<Integer>{
    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    public MatchState playerWon(int playerNumber) {
        setPlayerScore(playerNumber,getPlayerScore(playerNumber)+1);
        var playerScore = getPlayerScore(playerNumber); // Получаем счет нашего игрока
        if (playerScore == 6){
            var opposition = getOppositeScore(playerNumber);
            if (opposition < 5){
                return playerNumber == 0 ? MatchState.FIRST_PLAYER_WINS : MatchState.SECOND_PLAYER_WINS;
            }
        }
        else if (playerScore > 6 && playerScore - getOppositeScore(playerNumber) == 2){
            return playerNumber == 0 ? MatchState.FIRST_PLAYER_WINS : MatchState.SECOND_PLAYER_WINS;
        }

        return MatchState.NOT_OVER;
    }
}
