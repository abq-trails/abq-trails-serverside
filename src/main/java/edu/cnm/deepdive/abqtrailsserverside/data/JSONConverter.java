package edu.cnm.deepdive.abqtrailsserverside.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;

public class JSONConverter {

  ObjectMapper objectMapper = new ObjectMapper();

  TrailData trailData;

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
