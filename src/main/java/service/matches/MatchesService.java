package service.matches;

import dto.matches.CreateMathesDto;
import dto.matches.ReadMatchesDto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import mapper.matches.CreateMathesMapper;
import mapper.matches.ReadMatchesMapper;
import repository.matches.MathesRepository;
import repository.players.PlayersRepository;

import java.util.Collections;
import java.util.List;


@RequiredArgsConstructor
public class MatchesService {
    private final MathesRepository mathesRepository;
    private final CreateMathesMapper createMathesMapper;
    private final ReadMatchesMapper readMatchesMapper;
    private final PlayersRepository playersRepository;

    public static MatchesService openService(EntityManager entityManager){
        var mathesRepository = new MathesRepository(entityManager);
        var playersRepository = new PlayersRepository(entityManager);
        var createMathesMapper = new CreateMathesMapper(playersRepository);
        var readMatchesMapper = new ReadMatchesMapper();
        return new MatchesService(mathesRepository, createMathesMapper, readMatchesMapper, playersRepository);
    }

  public CreateMathesDto createMatch(CreateMathesDto createMathesDto){
      var matchesEntity = createMathesMapper.mapFrom(createMathesDto);
      mathesRepository.save(matchesEntity);
      return createMathesDto;
  }  public void createMatches(List<String> playersNames){
        for (int i = 0; i < playersNames.size()-1; i+=2) {
            boolean rnd = Math.random() > 0.5;
            var createMathesDto = CreateMathesDto.builder()
                    .player1(playersNames.get(i))
                    .player2(playersNames.get(i + 1))
                    .winner(rnd ? playersNames.get(i) : playersNames.get(i + 1))
                    .build();
            var matchesEntity = createMathesMapper.mapFrom(createMathesDto);
            mathesRepository.save(matchesEntity);
        }
  }

 public List<ReadMatchesDto> findAllMatches(){
     return mathesRepository.findAll()
             .stream()
             .map(readMatchesMapper::mapFrom)
             .toList();
 }

 public List<ReadMatchesDto> findMatchesWithPagination(int page){
         int offset = page * 7 - 7;
         int limit =  7;
         return mathesRepository.findMatchesWithPagination(offset,limit).stream()
                 .map(readMatchesMapper::mapFrom)
                 .toList();
 }

 public List<ReadMatchesDto> findMatchesByPlayerName(String playerName){
    var mabyPlayer = playersRepository.findByName(playerName);
    if (mabyPlayer.isPresent()){
       return mathesRepository.findByPlayerId(mabyPlayer.get().getId()).stream().map(readMatchesMapper::mapFrom).toList();
    }
    return Collections.emptyList();
 }

}
