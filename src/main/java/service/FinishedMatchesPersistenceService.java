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


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FinishedMatchesPersistenceService { // инкапсулирует чтение и запись законченных матчей в БД
  private static final FinishedMatchesPersistenceService INSTANCE = new FinishedMatchesPersistenceService();
   public static FinishedMatchesPersistenceService getInstance(){return INSTANCE;}

    public void finishMatch(CurrentMatches match){
        var createMathesDto = buildCreateMatchesDto(match);
        savePlayersIfDontExist(createMathesDto);
        saveMatch(createMathesDto);
    }
    public List<ReadMatchesDto> findAllMatches() {
        List<ReadMatchesDto> matches = new ArrayList<>();
        var sessionFactory = HibernateUtil.getSessionFactory();
        EntityManager entityManager =  (EntityManager) Proxy.newProxyInstance(Session.class.getClassLoader(),new Class[]{Session.class},
                ((proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(),args)));
        var matchesService = MatchesService.openService(entityManager);

        entityManager.getTransaction().begin();

        matches = matchesService.findAllMatches();

        entityManager.getTransaction().commit();
        return matches;
    }

    public List<ReadMatchesDto> findAllMatchesByPlayerNameWithPagination(String filterByPlayerName, int page) {
        List<ReadMatchesDto> matches = new ArrayList<>();
        var sessionFactory = HibernateUtil.getSessionFactory();
        EntityManager entityManager =  (EntityManager) Proxy.newProxyInstance(Session.class.getClassLoader(),new Class[]{Session.class},
                ((proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(),args)));
        var matchesService = MatchesService.openService(entityManager);

        entityManager.getTransaction().begin();

        matches = matchesService.findMatchesByPlayerName(filterByPlayerName, page);

        entityManager.getTransaction().commit();
        return matches;
    }
    public List<ReadMatchesDto> findAllMatchesByPlayerName(String filterByPlayerName) {
        List<ReadMatchesDto> matches = new ArrayList<>();
        var sessionFactory = HibernateUtil.getSessionFactory();
        EntityManager entityManager =  (EntityManager) Proxy.newProxyInstance(Session.class.getClassLoader(),new Class[]{Session.class},
                ((proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(),args)));
        var matchesService = MatchesService.openService(entityManager);

        entityManager.getTransaction().begin();

        matches = matchesService.findMatchesByPlayerName(filterByPlayerName);

        entityManager.getTransaction().commit();
        return matches;
    }
    public List<ReadMatchesDto> findAllMatchesWithPagination(int page) {
       List<ReadMatchesDto> matches = new ArrayList<>();
       var sessionFactory = HibernateUtil.getSessionFactory();
       EntityManager entityManager =  (EntityManager) Proxy.newProxyInstance(Session.class.getClassLoader(),new Class[]{Session.class},
                ((proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(),args)));
       var matchesService = MatchesService.openService(entityManager);

       entityManager.getTransaction().begin();

       matches = matchesService.findMatchesWithPagination(page);

        entityManager.getTransaction().commit();
        return matches;
    }

    public void savePlayersIfDontExist(CreateMathesDto createMathesDto){

        var sessionFactory = HibernateUtil.getSessionFactory();
        EntityManager entityManager =  (EntityManager) Proxy.newProxyInstance(Session.class.getClassLoader(),new Class[]{Session.class},
                ((proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(),args)));
        var playersService = PlayersService.openService(entityManager);
        entityManager.getTransaction().begin();

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

        entityManager.getTransaction().commit();
    }
    public void saveMatch(CreateMathesDto createMathesDto){

        var sessionFactory = HibernateUtil.getSessionFactory();
        EntityManager entityManager =  (EntityManager) Proxy.newProxyInstance(Session.class.getClassLoader(),new Class[]{Session.class},
                ((proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(),args)));
        var matchesService = MatchesService.openService(entityManager);

        entityManager.getTransaction().begin();

        matchesService.createMatch(createMathesDto);

        entityManager.getTransaction().commit();
    }
    private CreateMathesDto buildCreateMatchesDto(CurrentMatches matches){
        return CreateMathesDto.builder()
                .player1(matches.getPlayer1().getName())
                .player2(matches.getPlayer2().getName())
                .winner(matches.getWinner().getName())
                .build();
    }
}
