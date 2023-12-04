package entity.matches;

import entity.players.PlayersEntity;
import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Setter @Getter
public class MatchScore { // Класс описывающий счет матча


  private int [] points = new int[]{0,0};
  private int [] games = new int[]{0,0};
  private int [] sets = new int[]{0,0};
  private int [] tieBreak = new int[]{0,0};
  private boolean istieBreak = false;
  private boolean matchIsOver = false;
  private PlayersEntity winner = null;

   public void setTieBreak(){ // Запустить тай брейк
       istieBreak = true;
   }
   public void finishMatch(){ //закончить матч
       matchIsOver = true;
   }


}
