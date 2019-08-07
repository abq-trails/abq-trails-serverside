//Copyright 2019 Denelle Britton Linebarger, Alana Chigbrow, Anita Martin, David Nelson
//
//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.

package edu.cnm.deepdive.abqtrailsserverside.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;

/**
 * Converts object(Trail Data?) into JSON.
 */
public class JSONConverter {

  ObjectMapper objectMapper = new ObjectMapper();
  /**
   * Creates Object of ObjectMapper defined in Jackson Api
   */

  TrailData trailData;

  /** Trail Data */ {
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
