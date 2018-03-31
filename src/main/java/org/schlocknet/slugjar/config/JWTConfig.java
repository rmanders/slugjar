package org.schlocknet.slugjar.config;

import org.schlocknet.slugjar.service.JwtService;
import org.schlocknet.slugjar.service.JwtServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Configuration class for JSON web token service
 */
@Configuration
public class JWTConfig {

  /**
   * Local logger instance
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(JWTConfig.class);

  /**
   * Access to environmental properties
   */
  @Autowired
  private Environment env;


  /**
   * Creates an instance of the JSON Web Token Service
   *
   * @return
   */
  @Bean
   public JwtService jwtService() {
    LOGGER.debug("Starting JWT Service...");
    return new JwtServiceImpl(env.getRequiredProperty("jwt.secret"));
   }
}
