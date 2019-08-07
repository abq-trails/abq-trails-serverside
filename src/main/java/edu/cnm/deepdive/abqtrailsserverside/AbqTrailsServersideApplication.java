package edu.cnm.deepdive.abqtrailsserverside;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@SpringBootApplication
@EnableWebSecurity
@EnableResourceServer
public class AbqTrailsServersideApplication extends ResourceServerConfigurerAdapter {


  @Value("${oauth.clientId}")
  private String clientId;


  public static void main(String[] args) {
    SpringApplication.run(AbqTrailsServersideApplication.class, args);
  }

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources.resourceId(clientId);
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.authorizeRequests().anyRequest()
        .anonymous(); //TODO Change to hasRole("USER") from anonymous.
    //    http.authorizeRequests().anyRequest().hasRole("USER");
  }
}