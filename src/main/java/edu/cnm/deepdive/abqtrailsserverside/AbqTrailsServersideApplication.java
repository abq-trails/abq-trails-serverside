package edu.cnm.deepdive.abqtrailsserverside;

import edu.cnm.deepdive.abqtrailsserverside.data.TrailData;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@SpringBootApplication
public class AbqTrailsServersideApplication {

  public static void main(String[] args) {
    SpringApplication.run(AbqTrailsServersideApplication.class, args);
    getData();
  }

  private static void getData() {
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
    converter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON));

    final String url = "http://data.cabq.gov/community/opentrails/trail_segments.geojson";
    RestTemplate template = new RestTemplate();
    template.getMessageConverters().add(converter);
    TrailData result = template.getForObject(url, TrailData.class);
    System.out.println(result.type);
  }

}
