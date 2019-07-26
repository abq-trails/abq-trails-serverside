package edu.cnm.deepdive.abqtrailsserverside;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cnm.deepdive.abqtrailsserverside.data.TrailData;
import java.io.DataInput;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.geojson.FeatureCollection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

@Configuration
@SpringBootApplication
public class AbqTrailsServersideApplication {

  static ResponseEntity<byte[]> result;

  public static void main(String[] args) {
    SpringApplication.run(AbqTrailsServersideApplication.class, args);
    getData();
  }

  private static void getData() {

    final String url = "http://data.cabq.gov/community/opentrails/trail_segments.geojson";
    RestTemplate template = new RestTemplate();
    FeatureCollection collection = null;
    try {
      collection = template.execute(new URI(url), HttpMethod.GET, (RequestCallback) null, clientHttpResponse -> {
        File ret = File.createTempFile("download", "tmp");
        StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(ret));
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(ret, FeatureCollection.class);
      });
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    System.out.println(collection.getFeatures().size());

  }
}
