package edu.cnm.deepdive.abqtrailsserverside.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cnm.deepdive.abqtrailsserverside.model.dao.TrailRepository;
import edu.cnm.deepdive.abqtrailsserverside.model.entity.Trail;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.GeoJsonObject;
import org.geojson.Geometry;
import org.geojson.GeometryCollection;
import org.springframework.http.HttpMethod;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

public class DataMapper {

  private static FeatureCollection collection = null;

  public static FeatureCollection getData() {

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
    return collection;
  }

  public static void mapTrail(FeatureCollection collection, TrailRepository repository) {

//    List<Trail> trails = new LinkedList<>();

  for (Feature feature: collection.getFeatures()) {
    feature.getId();
    GeoJsonObject geometry = feature.getGeometry();
    Map properties = feature.getProperties();
    long cabqId = convertToLong(properties.get("id"));
    if (repository.findByCabqId(cabqId) == null) {
      Trail trail = new Trail();
      trail.setCabqId(cabqId);
      if (properties.get("name") != null) {
        trail.setName(properties.get("name").toString());
      }
//      if (geometry != null) {
//        trail.setCoordinates(geometry);
//      }
      if (properties.get("bicycle") != null) {
        trail.setBike(convertToBool(properties.get("bicycle")));
      }
      if (properties.get("horse") != null) {
        trail.setHorse(convertToBool(properties.get("horse")));
      }
      repository.save(trail);
    }
  }
  }

  public static Long convertToLong(Object o) {
    return new Long(o.toString());
  }
  public static boolean convertToBool(Object o) {
    String status = o.toString();
    return status.equals("yes");
  }

}
