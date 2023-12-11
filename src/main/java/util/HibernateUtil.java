package util;

import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {
    private SessionFactory sessionFactory;
    private final static String DRIVER_NAME_KEY = "db.driver";

static {
    loadDriver();
    DataImporterUtil.importData();
}
public SessionFactory getSessionFactory(){
       if (sessionFactory == null){
           createSessionFactory();
       }
       return sessionFactory;
}

 private SessionFactory createSessionFactory(){
     Configuration configuration = buildConfiguration();
     configuration.configure();
     sessionFactory = configuration.buildSessionFactory();
     return sessionFactory;
 }

  private Configuration buildConfiguration(){
      Configuration configuration = new Configuration();
      return configuration;
  }
    private static void loadDriver() {
        try {
            Class.forName(PropertiesUtil.get(DRIVER_NAME_KEY));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
