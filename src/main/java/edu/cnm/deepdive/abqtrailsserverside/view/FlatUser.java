package edu.cnm.deepdive.abqtrailsserverside.view;

import java.net.URI;
import java.util.Date;
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
