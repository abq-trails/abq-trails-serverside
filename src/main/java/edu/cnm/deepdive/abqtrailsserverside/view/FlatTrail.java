package edu.cnm.deepdive.abqtrailsserverside.view;

import java.net.URI;
import java.util.Date;
import java.util.UUID;

public interface FlatTrail {

  UUID getId();

  Date getCreated();

  Date getUpdated();

  String getName();

  long getCabqId();

  double getLength();

  boolean isHorse();

  boolean isBike();

  double getRating();

  URI getHref();
}
