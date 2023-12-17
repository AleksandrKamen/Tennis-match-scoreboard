package service;

import current_matches.CurrentMatches;
import exception.ValidationException;
import jakarta.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import matches.dto.CreateMathesDto;
import matches.dto.ReadMatchesDto;
import matches.service.MatchesService;
import org.hibernate.Session;
import players.dto.CreatePlayersDto;
import players.service.PlayersService;
import util.HibernateUtil;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;


public class FinishedMatchesPersistenceService {
    private static final MatchesService matchService = new MatchesService();
    private static final PlayersService playersService = new PlayersService();

    public void finishMatch(CurrentMatches match){
        var createMathesDto = buildCreateMatchesDto(match);
        savePlayersIfDontExist(createMathesDto);
        saveMatch(createMathesDto);
    }
    public List<ReadMatchesDto> findAllMatches() {
        return matchService.findAllMatches();
    }

    public List<ReadMatchesDto> findAllMatchesByPlayerNameWithPagination(String filterByPlayerName, int page) {
      return  matchService.findMatchesByPlayerName(filterByPlayerName, page);
    }
    public List<ReadMatchesDto> findAllMatchesByPlayerName(String filterByPlayerName) {
      return matchService.findMatchesByPlayerName(filterByPlayerName);
    }
    public List<ReadMatchesDto> findAllMatchesWithPagination(int page) {
       return matchService.findMatchesWithPagination(page);
    }

    public void savePlayersIfDontExist(CreateMathesDto createMathesDto){

    try {
        playersService.createPlayer(CreatePlayersDto
                .builder()
                .name(createMathesDto.getPlayer1())
                .build());
    } catch (ValidationException e){}
    try {
        playersService.createPlayer(
                CreatePlayersDto
                        .builder()
                        .name(createMathesDto.getPlayer2())
                        .build());
    } catch (ValidationException e){}

    }
    public void saveMatch(CreateMathesDto createMathesDto){
        matchService.createMatch(createMathesDto);
    }
    private CreateMathesDto buildCreateMatchesDto(CurrentMatches matches){
        return CreateMathesDto.builder()
                .player1(matches.getPlayer1().getName())
                .player2(matches.getPlayer2().getName())
                .winner(matches.getWinner().getName())
                .build();
    }
}
