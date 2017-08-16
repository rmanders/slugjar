package org.schlocknet.slugjar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;

/**
 * Primary database configuration class
 */
@Configuration
public class DatabaseConfig {

  /** Access to envirnoment variable and properties */
  @Autowired
  Environment env;

  @Bean
  MongoClientFactoryBean mongo() {

  }
}
