package org.schlocknet.slugjar.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

//@Profile("dev")
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  /** Local logger */
  private static final Logger LOGGER = LoggerFactory.getLogger(SwaggerConfig.class);

  @Bean
  public Docket slugjarApi() {
    LOGGER.info("Creating swagger-ui docket");

    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("org.schlocknet.slugjar.controller"))
        .paths(regex("/.*"))
        .build()
        .apiInfo(apiMetadata());
  }

  private ApiInfo apiMetadata() {
    ApiInfoBuilder builder = new ApiInfoBuilder();
    return builder
        .description("Multipurpose Web Services")
        .title("Slugjar")
        .version("1.0.0")
        .build();
  }
}
