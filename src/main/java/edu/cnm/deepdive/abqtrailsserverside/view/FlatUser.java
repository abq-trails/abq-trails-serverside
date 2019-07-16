package edu.cnm.deepdive.abqtrailsserverside.view;

import edu.cnm.deepdive.abqtrailsserverside.model.entity.Photo;
import edu.cnm.deepdive.abqtrailsserverside.model.entity.Rating;
import edu.cnm.deepdive.abqtrailsserverside.model.entity.User;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface FlatUser {

  UUID getId();

  Date getCreated();

  Date getUpdated();

  String getUsername();

  String getAuthenticatedId();

  String getFirstName();

  String getLastName();

  URI getHref();

}
