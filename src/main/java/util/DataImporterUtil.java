package util;

import lombok.experimental.UtilityClass;
import matches.dto.CreateMathesDto;
import service.FinishedMatchesPersistenceService;
import java.util.List;
import java.util.Random;

@UtilityClass
public class DataImporterUtil {
    FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
    public void importData (){
        for (int i = 0; i <50; i++) {
            var match = getCreateMatchesDto();
            finishedMatchesPersistenceService.savePlayersIfDontExist(match);
            finishedMatchesPersistenceService.saveMatch(match);
        }
    }
    public CreateMathesDto getCreateMatchesDto(){
          var random = new Random();
          var names = List.of("Novak Djokovic", "Rafael Nadal","Daniil Medvedev","Carlos Alcaraz Garfia",
                "Stefanos Tsitsipas","Sebastian Korda","Tomas Martin Etcheverry", "Sebastian Ofner","Taylor Harry Fritz",
                "Dominic Stephan Stricker","Quentin Halys", "Matteo Berrettini","Albert Ramos-Vinolas","Jeff Wolf",
                "Alex Michelsen","Jaume Antoni Munar Clar", "Christian Garin","Bernabe Zapata Miralles","Dominik Koepfer",
                "Constant Lestienne","Taro Daniel", "Benjamin Bonzi","Tomas Machac","Gasquet Richard","Luca Van Assche",
                "Wawrinka Stanislas","Daniel Altmaier","Marton Fucsovics","Yoshihito Nishioka","Zhizhen Zhang","Roman Safiullin",
                "Christopher Eubanks");

          var firstPlayer = names.get(random.nextInt(names.size()));
          var secondPlayer = names.get(random.nextInt(names.size()));
          while (firstPlayer.equals(secondPlayer)){
              firstPlayer = names.get(random.nextInt(names.size()));
              secondPlayer = names.get(random.nextInt(names.size()));
          }
          var winner = random.nextInt(2) == 0?firstPlayer:secondPlayer;


    return CreateMathesDto.builder()
        .player1(firstPlayer)
        .player2(secondPlayer)
        .winner(winner)
        .build();

    }

}
