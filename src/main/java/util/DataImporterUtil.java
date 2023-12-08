package util;

import jakarta.persistence.EntityManager;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import matches.service.MatchesService;
import players.service.PlayersService;

import java.lang.reflect.Proxy;
import java.util.List;
@UtilityClass
public class DataImporterUtil {
    public void importData(){
         var sessionFactory = HibernateUtil.getSessionFactory();
         EntityManager entityManager =  (EntityManager) Proxy.newProxyInstance(Session.class.getClassLoader(),new Class[]{Session.class},
                    ((proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(),args)));
            var matchesService = MatchesService.openService(entityManager);
            var playersService = PlayersService.openService(entityManager);

            entityManager.getTransaction().begin();
            var names = List.of("Novak Djokovic", "Rafael Nadal","Daniil Medvedev","Carlos Alcaraz Garfia",
             "Stefanos Tsitsipas","Sebastian Korda","Tomas Martin Etcheverry", "Sebastian Ofner","Taylor Harry Fritz",
             "Dominic Stephan Stricker","Quentin Halys", "Matteo Berrettini","Albert Ramos-Vinolas","Jeff Wolf",
             "Alex Michelsen","Jaume Antoni Munar Clar", "Christian Garin","Bernabe Zapata Miralles","Dominik Koepfer",
             "Constant Lestienne","Taro Daniel", "Benjamin Bonzi","Tomas Machac","Gasquet Richard","Luca Van Assche",
             "Wawrinka Stanislas","Daniel Altmaier","Marton Fucsovics","Yoshihito Nishioka","Zhizhen Zhang","Roman Safiullin",
             "Christopher Eubanks");

            playersService.createPlayers(names);
            matchesService.createMatches(names);

            entityManager.getTransaction().commit();

    }
}
