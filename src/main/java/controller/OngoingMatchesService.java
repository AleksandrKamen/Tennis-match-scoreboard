package controller;

import dto.current_matches.CreateCurrentMatchesDto;
import lombok.RequiredArgsConstructor;
import controller.current_matches.CreateCurrentMatchesMapper;

@RequiredArgsConstructor
public class OngoingMatchesService {
   private final CurrentMatchesRepository currentMatchesRepository;
   private final CreateCurrentMatchesMapper createCurrentMatchesMapper;

   public CreateCurrentMatchesDto createNewMatch(String player1Name, String player2Name){
      //validation
      var createCurrentMatchesDto = CreateCurrentMatchesDto.builder()
              .player1Name(player1Name)
              .player2Name(player2Name)
              .build();
      var currentMatchEntity = createCurrentMatchesMapper.mapFrom(createCurrentMatchesDto);
      currentMatchesRepository.save(currentMatchEntity);

      return createCurrentMatchesDto;
   }








}
