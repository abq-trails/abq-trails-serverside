package edu.cnm.deepdive.abqtrailsserverside.view;

import java.net.URI;
import java.util.Date;
import java.util.UUID;
import org.geojson.GeoJsonObject;

/**
 * Declares the getters (and thus the JSON properties) of a trail for serialization, excluding
 * references to other objects that could result in stack or buffer overflow on serialization.
 */
public interface FlatTrail {

  UUID getId();

  Date getCreated();

  Date getUpdated();

  String getName();

  Long getCabqId();

  double getLength();

  boolean isHorse();

  boolean isBike();

  double getRating();

  URI getHref();
}
