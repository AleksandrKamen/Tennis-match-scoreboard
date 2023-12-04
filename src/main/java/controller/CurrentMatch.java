package controller;

import dto.players.CreatePlayersDto;
import lombok.Data;
@Data
public class CurrentMatch {

    private CreatePlayersDto player1;
    private CreatePlayersDto player2;
    private MatchScore score;
}
