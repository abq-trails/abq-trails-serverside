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

package edu.cnm.deepdive.abqtrailsserverside.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cnm.deepdive.abqtrailsserverside.model.dao.TrailRepository;
import edu.cnm.deepdive.abqtrailsserverside.model.entity.Trail;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.GeoJsonObject;
import org.springframework.http.HttpMethod;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

/**
 * Pulls from the Cabq Data source and populates the trail table.
 */
public class DataMapper {

  private static FeatureCollection collection = null;

  /**
   * Makes the call to the Cabq data and puts it into a feature collection.
   * @return
   */
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

  /**
   * Adds feature to trail entity.
   * @param collection FeatureCollection.
   * @param repository trail repository.
   * @throws JsonProcessingException
   */
  public static void mapTrail(FeatureCollection collection, TrailRepository repository)
      throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();

//    List<Trail> trails = new LinkedList<>();

  for (Feature feature: collection.getFeatures()) {
    feature.getId();
    GeoJsonObject geometry = feature.getGeometry();
    Map properties = feature.getProperties();
    long cabqId = convertToLong(properties.get("id"));
    if (!repository.findByCabqId(cabqId).isPresent()) {
      Trail trail = new Trail();
      trail.setCabqId(cabqId);
      if (properties.get("name") != null) {
        trail.setName(properties.get("name").toString());
      }
      if (geometry != null) {
//        System.out.println(geometry.toString());
        String json = mapper.writeValueAsString(geometry);
//        Geometry geo = reader.read(json);
        trail.setCoordinates(json);
//        geometry.
//        trail.setCoordinates(GeometryFactory);
//        trail.setCoordinates(geometry);
      }
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

  /**
   * Converts the id from the CABQ data from an object to a long.
   * @param o the id property from the FeatureCollection.
   * @return returns a long value.
   */
  public static Long convertToLong(Object o) {
    String str = o.toString();
    long id = Long.parseLong(str);
    return id;
  }

  /**
   * Converts the yes/no string values for horse and bike properties in the CABQ data to a boolean value.
   * @param o The horse and/or bike property objects from the FeatureCollection.
   * @return a boolean value.
   */
  public static boolean convertToBool(Object o) {
    String status = o.toString();
    return status.equals("yes");
  }


}
