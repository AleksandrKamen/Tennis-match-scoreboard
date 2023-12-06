package matches.entity;

import players.entity.PlayersEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "matches")
public class MatchesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "player1", nullable = false)
    private PlayersEntity player1;
    @ManyToOne
    @JoinColumn(name = "player2", nullable = false)
    private PlayersEntity player2;
    @ManyToOne
    @JoinColumn(name = "winner", nullable = false)
    private PlayersEntity winner;

}
