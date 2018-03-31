package org.schlocknet.slugjar.config.db;

import org.dizitart.no2.Nitrite;
import org.schlocknet.slugjar.dao.BlogPostDao;
import org.schlocknet.slugjar.dao.BlogPostDaoNitriteImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class NitriteDbConfig {

  /** Local logger instance */
  private static final Logger LOGGER = LoggerFactory.getLogger(NitriteDbConfig.class);

  /** The default database path */
  public static final String DEFAULT_DB_FILEPATH = "./slugjar-db.no2";

  /** Database filepath property */
  public static final String PROP_DB_FILE_PATH = "database.filePath";

  /** Username property for the database */
  public static final String PROP_DB_USERNAME = "database.username";

  /** Password/crdentials property for teh database */
  public static final String PROP_DB_CREDENTIALS = "database.password";


  /** Provides access to environment and properties */
  private final Environment env;

  /**
   * Default constructor
   * @param env
   */
  @Autowired
  public NitriteDbConfig(Environment env) {
    this.env = env;
  }

  /**
   * Creates an instance of the nitrite database
   * @return
   */
  @Bean
  public Nitrite nitriteDb() {

    final String dbFilePath = env.getProperty(PROP_DB_FILE_PATH, DEFAULT_DB_FILEPATH);
    final String dbUsername = env.getProperty(PROP_DB_USERNAME, "");
    final String dbPassword = env.getProperty(PROP_DB_CREDENTIALS, "");

    LOGGER.info("Creating Nitrite Database - file: [{}], Username: [{}]", dbFilePath, dbUsername);
    return Nitrite.builder()
        .compressed()
        .filePath(dbFilePath)
        .openOrCreate(dbUsername, dbPassword);
  }

  @Bean
  public BlogPostDao blogPostDao() {
    return new BlogPostDaoNitriteImpl(nitriteDb());
  }
}
