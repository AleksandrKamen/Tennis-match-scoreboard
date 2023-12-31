package current_matches.score;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public abstract class Score<T> {
    @Getter
    private final List<T> playerScore = new ArrayList<>();

    protected abstract T getZeroScore();

    public Score() {
        playerScore.add(getZeroScore());
        playerScore.add(getZeroScore());
    }

    public T getPlayerScore(int playerNumber) {
        return playerScore.get(playerNumber);
    }

    public T getOppositeScore(int playerNumber) {
        return playerScore.get((playerNumber == 0 ? 1 : 0));
    }

    public void setPlayerScore(int playerNumber, T score) {
        playerScore.set(playerNumber, score);
    }

    public void setOppositeScore(int playerNumber, T score) {
        playerScore.set(playerNumber == 0 ? 1 : 0, score);
    }

    public abstract MatchState pointWon(int playerNumber);

}
