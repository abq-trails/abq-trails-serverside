package edu.cnm.deepdive.abqtrailsserverside.data;

import java.util.List;
import java.util.Map;

public class TrailData {

  public List<Feature> features;
  public String type;

  public static class Feature {
   public Map<String, Object> geometry;
   public Properties properties;
   public String type;
  }

//  public static class Geometry {
//    public List<List<List<Double>>> coordinates;
//    public String type;
//  }

  public static class Properties {

    public String bicycle;
    public String foot;
    public String horse;
    public String id;
    public String motorVehicles;
    public String name;
    public String osmTags;
    public String ski;
    public String stewardId;
    public String wheelchair;
  }

}
