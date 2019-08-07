/*
 * Copyright 2019 Denelle Britton Linebarger, Alana Chigbrow, Anita Martin, David Nelson
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

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

/**
 * Called when this application is starting??
 */
@Configuration
@SpringBootApplication
@EnableWebSecurity
@EnableResourceServer
public class AbqTrailsServersideApplication extends ResourceServerConfigurerAdapter {

//  static ResponseEntity<byte[]> result;

  @Value("${oauth.clientId}")
  private String clientId;

  /**
   *
   */
  public static void main(String[] args) {
    SpringApplication.run(AbqTrailsServersideApplication.class, args);
//   getData();
  }

//  private static void getData() {
//
//    final String url = "http://data.cabq.gov/community/opentrails/trail_segments.geojson";
//    RestTemplate template = new RestTemplate();
//    FeatureCollection collection = null;
//    try {
//      collection = template.execute(new URI(url), HttpMethod.GET, (RequestCallback) null, clientHttpResponse -> {
//        File ret = File.createTempFile("download", "tmp");
//        StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(ret));
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.readValue(ret, FeatureCollection.class);
//      });
//    } catch (URISyntaxException e) {
//      e.printStackTrace();
//    }
//    System.out.println(collection.getFeatures().size());
//
//  }

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources.resourceId(clientId);
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.authorizeRequests().anyRequest()
        .anonymous(); //TODO Change to hasRole("USER") from anonymous.
  }
}
