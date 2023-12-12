package service;

import current_matches.score.MatchState;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchScoreCalculationService {
    private static final MatchScoreCalculationService INSTANCE = new MatchScoreCalculationService();
    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();

  public MatchState updateScore(UUID uuid, int plalerNumber){
      var currentMatch = ongoingMatchesService.getMatch(uuid).get();
      var matchState = currentMatch.getScore().pointWon(plalerNumber);

      if (matchState != MatchState.NOT_OVER){
          currentMatch.setWinner(plalerNumber);
      }
      return matchState;
  }
    public static MatchScoreCalculationService getInstance() {
        return INSTANCE;
    }
}
