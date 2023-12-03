package service;

import dto.matches.CreateMathesDto;
import dto.matches.ReadMatchesDto;
import lombok.RequiredArgsConstructor;
import mapper.matches.CreateMathesMapper;
import mapper.matches.ReadMatchesMapper;
import repository.MathesRepository;
import repository.PlayersRepository;
import validator.ValidationResult;

import java.util.List;
@RequiredArgsConstructor
public class FinishedMatchesPersistenceService { // инкапсулирует чтение и запись законченных матчей в БД
    private final MathesRepository mathesRepository;
    private final ReadMatchesMapper readMatchesMapper;
    private final CreateMathesMapper createMathesMapper;
    private final PlayersRepository playersRepository;


    public boolean writeMatch(CreateMathesDto finishedMatch){
     // TODO: 03.12.2023 Валидация finishedMatch
    //       ValidationResult validationResult = readExchangeRateValidator.isValid(currenciesByCodes);
   //       if (!validationResult.isValid()){
  //           throw new  ValidationException(validationResult.getErrors());
 //       }

       var matchesEntity = createMathesMapper.mapFrom(finishedMatch);
       return mathesRepository.save(matchesEntity) != null;
    }
    public List<ReadMatchesDto> readAllMatches(){
        return mathesRepository.findAll().stream()
                .map(readMatchesMapper::mapFrom)
                .toList();
    }
        public List<ReadMatchesDto> readMatchByPlayerName(String playerName){
        // TODO: 03.12.2023 Валидация playerName
       //       ValidationResult validationResult = readExchangeRateValidator.isValid(currenciesByCodes);
      //       if (!validationResult.isValid()){
     //           throw new  ValidationException(validationResult.getErrors());
    //       }

       var playersEntity = playersRepository.findByName(playerName).get();
       return   mathesRepository.findByPlayerId(playersEntity.getId()).stream()
               .map(readMatchesMapper::mapFrom)
               .toList();
    }
}
