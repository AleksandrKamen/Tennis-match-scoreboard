package current_matches;

import lombok.Getter;
import players.dto.CreatePlayersDto;

@Getter
public class MatchScore { // Класс описывающий счет матча

  private State gameState = State.NOT_OVER;
  private boolean isTieBreak = false;
  private boolean matchIsOver = false;
  private CreatePlayersDto winner = null;

   public void setTieBreak(){ // Запустить тай брейк
       isTieBreak = true;
   }
   public void finishMatch(){ //закончить матч
       matchIsOver = true;
   }


}
