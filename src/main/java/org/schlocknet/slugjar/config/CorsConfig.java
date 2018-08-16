package org.schlocknet.slugjar.config;

import java.util.Arrays;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CorsConfig
{

  /**
   * Local logger instance
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(CorsConfig.class);

  private final Environment env;

  public CorsConfig(@NotNull Environment env)
  {
    this.env = env;
  }

  @Bean
  public WebMvcConfigurer corsConfigurer()
  {
    return new WebMvcConfigurerAdapter()
    {
      @Override
      public void addCorsMappings(final CorsRegistry registry)
      {
        if (Arrays.stream(env.getActiveProfiles()).anyMatch(it -> "dev".equalsIgnoreCase(it)))
        {
          LOGGER.warn("Detected 'dev' environment. Allowing all origins for CORS");
          registry.addMapping("/**").allowedOrigins("*");
        }
        super.addCorsMappings(registry);
      }
    };
  }
}
