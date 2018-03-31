package org.schlocknet.slugjar.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Primary database configuration class
 */
//@Configuration
//@EnableMongoRepositories(basePackages = {"org.schlocknet.slugjar.dao"})
public class DatabaseConfig {

  /** Local logger */
  private static Logger LOGGER = LoggerFactory.getLogger(DatabaseConfig.class);

  /** Access to environment variable and properties */
  //@Autowired
  Environment env;

  //@Bean
  MongoClientFactoryBean mongo() {
    LOGGER.debug("Creating mongo client factory bean");
    MongoClientFactoryBean mongo = new MongoClientFactoryBean();
    mongo.setHost(env.getRequiredProperty("db.mongo.host"));
    return mongo;
  }

  //@Bean
  MongoTemplate mongoTemplate(Mongo mongo) {
    LOGGER.debug("Creating MongoTemplate");
    return new MongoTemplate(mongo, env.getProperty("db.mongo.database", "slugjar"));
  }

  /** Used for validating MongoDB documents */
  //@Bean
  public ValidatingMongoEventListener validatingMongoEventListener() {
    return new ValidatingMongoEventListener(mongoValidator());
  }

  //@Bean
  public LocalValidatorFactoryBean mongoValidator() {
    return new LocalValidatorFactoryBean();
  }

}
