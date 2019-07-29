package edu.cnm.deepdive.abqtrailsserverside.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import org.geojson.FeatureCollection;
import org.springframework.http.HttpMethod;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

public class DataMapper {

  static FeatureCollection collection = null;

  private static void getData() {

    final String url = "http://data.cabq.gov/community/opentrails/trail_segments.geojson";
    RestTemplate template = new RestTemplate();
//    FeatureCollection collection = null;
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

  private static void mapTrail(FeatureCollection collection) {

  }

}
