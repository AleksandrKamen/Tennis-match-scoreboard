package controller.current_matches;

import dto.current_matches.CreateCurrentMatchesDto;
import controller.CurrentMatchEntity;
import entity.matches.MatchScore;
import entity.players.PlayersEntity;
import lombok.RequiredArgsConstructor;
import mapper.Mapper;
import repository.players.PlayersRepository;
@RequiredArgsConstructor
public class CreateCurrentMatchesMapper implements Mapper<CreateCurrentMatchesDto, CurrentMatchEntity> {
   private final PlayersRepository playersRepository;

    @Override
    public CurrentMatchEntity mapFrom(CreateCurrentMatchesDto object) {
        PlayersEntity player1;
        PlayersEntity player2;

        if (playersRepository.findByName(object.getPlayer1Name()).isPresent()){
            player1 = playersRepository.findByName(object.getPlayer1Name()).get();
        } else{
            player1 = PlayersEntity.builder().name(object.getPlayer1Name()).build();
            playersRepository.save(player1);
        }

        if (playersRepository.findByName(object.getPlayer2Name()).isPresent()){
            player2 = playersRepository.findByName(object.getPlayer2Name()).get();
        } else {
            player2 = PlayersEntity.builder().name(object.getPlayer2Name()).build();
            playersRepository.save(player2);
        }

        return CurrentMatchEntity.builder()
                .player1(player1)
                .player2(player2)
                .score(new MatchScore())
                .build();
    }
}
