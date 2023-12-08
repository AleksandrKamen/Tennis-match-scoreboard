package service;


import current_matches.CurrentMatches;
import matches.dto.CreateMathesDto;
import players.dto.CreatePlayersDto;
import jakarta.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import matches.service.MatchesService;
import players.service.PlayersService;
import util.HibernateUtil;

import java.lang.reflect.Proxy;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FinishedMatchesPersistenceService { // инкапсулирует чтение и запись законченных матчей в БД

  private static final FinishedMatchesPersistenceService INSTANCE = new FinishedMatchesPersistenceService();
  private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();


   public static FinishedMatchesPersistenceService getInstance(){return INSTANCE;}

    public void finishMatch(UUID uuid){
        //todo Валидация на то что есть такой матч
        var createMathesDto = getCreateMatchesDto(ongoingMatchesService.getMatch(uuid).get());

        savePlayers(createMathesDto);
        saveMatch(createMathesDto);

        ongoingMatchesService.removeMatch(uuid);
    }

    private void savePlayers(CreateMathesDto createMathesDto){

        var sessionFactory = HibernateUtil.getSessionFactory();
        EntityManager entityManager =  (EntityManager) Proxy.newProxyInstance(Session.class.getClassLoader(),new Class[]{Session.class},
                ((proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(),args)));
        var playersService = PlayersService.openService(entityManager);
        entityManager.getTransaction().begin();
        try {
            playersService.createPlayer(CreatePlayersDto.builder().name(createMathesDto.getPlayer1()).build());
        } catch (Exception e){}
        try {
            playersService.createPlayer(CreatePlayersDto.builder().name(createMathesDto.getPlayer2()).build());
        } catch (Exception e){}
        entityManager.getTransaction().commit();
    }
    private void saveMatch(CreateMathesDto createMathesDto){

        var sessionFactory = HibernateUtil.getSessionFactory();
        EntityManager entityManager =  (EntityManager) Proxy.newProxyInstance(Session.class.getClassLoader(),new Class[]{Session.class},
                ((proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(),args)));

        var matchesService = MatchesService.openService(entityManager);
        entityManager.getTransaction().begin();
        matchesService.createMatch(createMathesDto);
        entityManager.getTransaction().commit();
    }

    private CreateMathesDto getCreateMatchesDto(CurrentMatches matches){
        return CreateMathesDto.builder()
                .player1(matches.getPlayer1().getName())
                .player2(matches.getPlayer2().getName())
//                .winner(matches.getScore().getWinner())
                .build();
    }
}
