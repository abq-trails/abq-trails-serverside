package edu.cnm.deepdive.abqtrailsserverside.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;

/**
 * Converts object(Trail Data?) into JSON.
 */
public class JSONConverter {

  ObjectMapper objectMapper = new ObjectMapper(); /** Creates Object of ObjectMapper defined in Jackson Api */

  TrailData trailData; /** Trail Data */

  {
    try {
      trailData = objectMapper
            .readValue(new URL("http://data.cabq.gov/community/opentrails/trail_segments.geojson"),
                TrailData.class);
      System.out.print(trailData);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
