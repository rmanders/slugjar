package org.schlocknet.slugjar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.bind.annotation.RequestMethod;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .exceptionHandling().and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().cors().disable()
            .authorizeRequests()
            .antMatchers(
                HttpMethod.GET,
                "/",
                "/*.html",
                "/favicon.ico",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js",
                "/info*",
                "/api/**/*"
            ).permitAll()
            .antMatchers(HttpMethod.POST, "/api/user/login").permitAll();
            //.anyRequest().authenticated();

        http
            .headers()
            .cacheControl();
    }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      // Do Nothing
  }
}
