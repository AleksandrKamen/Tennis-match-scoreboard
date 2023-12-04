package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "current_matches")
public class CurrentMatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "player1_id", nullable = false)
    private PlayersEntity player1;
    @ManyToOne
    @JoinColumn(name = "player2_id", nullable = false)
    private PlayersEntity player2;
    @Column(nullable = false)
    private MatchScore score;
}
