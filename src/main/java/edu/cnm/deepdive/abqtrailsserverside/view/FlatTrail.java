package edu.cnm.deepdive.abqtrailsserverside.view;

import java.net.URI;
import java.util.Date;
import java.util.UUID;
import org.geojson.GeoJsonObject;

public interface FlatTrail {

  UUID getId();

  Date getCreated();

  Date getUpdated();

  String getName();

  Long getCabqId();

  GeoJsonObject getCoordinates();

  double getLength();

  boolean isHorse();

  boolean isBike();

  double getRating();

  URI getHref();
}
