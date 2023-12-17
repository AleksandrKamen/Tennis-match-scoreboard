package service;

import current_matches.score.MatchState;
import java.util.UUID;
public class MatchScoreCalculationService {
  private static final OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
  public MatchState updateScore(UUID uuid, int plalerNumber){
      var currentMatch = ongoingMatchesService.getMatch(uuid).get();
      var matchState = currentMatch.getScore().pointWon(plalerNumber);

      if (matchState != MatchState.NOT_OVER){
          currentMatch.setWinner(plalerNumber);
      }
      return matchState;
  }

}
