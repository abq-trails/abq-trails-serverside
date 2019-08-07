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

import java.util.Map;
import org.geojson.FeatureCollection;

/**
 *
 */
public class TrailData {

  public FeatureCollection features;
  /**
   *
   */
  public String type; /**    */

  /**
   *
   */
  public static class Feature {

    public Map<String, Object> geometry;
    /**
     *
     */
    public Properties properties;
    /**
     *
     */
    public String type; /**    */
  }

//  public static class Geometry {
//    public List<List<List<Double>>> coordinates;
//    public String type;
//  }

  /**
   *
   */
  public static class Properties {

    /**
     *
     */
    public String bicycle;
    /**
     *
     */
    public String foot;
    /**
     *
     */
    public String horse;
    /**
     *
     */
    public String id;
    /**
     *
     */
    public String motorVehicles;
    /**
     *
     */
    public String name;
    /**
     *
     */
    public String osmTags;
    /**
     *
     */
    public String ski;
    /**
     *
     */
    public String stewardId;
    /**
     *
     */
    public String wheelchair; /**    */
  }

}
