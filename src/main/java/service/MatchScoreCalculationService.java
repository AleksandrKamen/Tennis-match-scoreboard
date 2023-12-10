package service;

import current_matches.score.MatchState;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchScoreCalculationService {
    private static final MatchScoreCalculationService INSTANCE = new MatchScoreCalculationService();
    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService = FinishedMatchesPersistenceService.getInstance();

  public void updateScore(UUID uuid, int plalerNumber){
      var currentMatch = ongoingMatchesService.getMatch(uuid).get();
      var matchState = currentMatch.getScore().pointWon(plalerNumber);

      if (matchState == MatchState.FIRST_PLAYER_WINS || matchState == MatchState.SECOND_PLAYER_WINS){
          currentMatch.setWinner(plalerNumber);
          finishedMatchesPersistenceService.finishMatch(currentMatch);
          ongoingMatchesService.removeMatch(currentMatch.getUuid());
      }
  }
    public static MatchScoreCalculationService getInstance() {
        return INSTANCE;
    }
}
