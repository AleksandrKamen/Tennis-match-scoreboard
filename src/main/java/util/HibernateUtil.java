package util;

import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {


 public static SessionFactory createSessionFactory(){
     Configuration configuration = buildConfiguration();
     configuration.configure();
     return configuration.buildSessionFactory();
 }

  public static Configuration buildConfiguration(){
      Configuration configuration = new Configuration();
      return configuration;
  }


}
