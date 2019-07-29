package edu.cnm.deepdive.abqtrailsserverside.view;

import java.net.URI;
import java.util.Date;
import java.util.UUID;

public interface FlatTrail {

  UUID getId();

  Date getCreated();

  Date getUpdated();

  String getName();

  double getLength();

  boolean isHorse();

  boolean isDog();

  boolean isBike();

  String getTrailHead();

  double getRating();

  URI getHref();
}
