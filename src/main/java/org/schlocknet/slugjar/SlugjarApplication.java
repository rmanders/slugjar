package org.schlocknet.slugjar;

import org.schlocknet.slugjar.util.ApplicationEnvironment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class SlugjarApplication {

  public static void main(String[] args) {
    ApplicationEnvironment.initializeFleet();
    SpringApplication.run(SlugjarApplication.class);
  }
}
