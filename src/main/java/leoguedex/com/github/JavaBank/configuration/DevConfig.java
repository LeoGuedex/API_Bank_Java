package leoguedex.com.github.JavaBank.configuration;

import java.text.ParseException;
import leoguedex.com.github.JavaBank.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

  @Autowired
  private DBService dbService;

  @Value("${spring.jpa.hibernate.ddl-auto}")
  private String strategy;

  @Bean
  public boolean instantiateDatabase() throws ParseException {
    if (!"create".equals(strategy)) {
      return false;
    }
    dbService.instantiateDatabase();
    return true;
  }

}
