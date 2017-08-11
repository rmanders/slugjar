package org.schlocknet.slugjar.config;

import org.schlocknet.slugjar.util.ApplicationEnvironment;
import org.schlocknet.slugjar.util.ApplicationInfoContributor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for spring actuation endpoints
 */
@Configuration
public class SpringActuatorConfig {

  /** Local logger */
  private static final Logger LOGGER = LoggerFactory.getLogger(SpringActuatorConfig.class);

  /** Creates an info contributor bean */
  @Bean
  public ApplicationInfoContributor applicationInfoContributor () {
    return new ApplicationInfoContributor(ApplicationEnvironment.loadApplicationInfoFromManifest(SpringActuatorConfig.class));
  }

}
