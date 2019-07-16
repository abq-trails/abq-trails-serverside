package edu.cnm.deepdive.abqtrailsserverside.view;

import edu.cnm.deepdive.abqtrailsserverside.model.entity.Photo;
import edu.cnm.deepdive.abqtrailsserverside.model.entity.Rating;
import java.net.URI;
import java.util.Date;
import java.util.List;
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
