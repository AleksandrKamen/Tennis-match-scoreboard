package util;

import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {
    private SessionFactory sessionFactory;

static {
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
}
