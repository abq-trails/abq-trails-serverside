package edu.cnm.deepdive.abqtrailsserverside.view;

import java.net.URI;
import java.util.Date;
import java.util.UUID;

/**
 * Declares the getters (and thus the JSON properties) of a user for serialization, excluding
 * references to other objects that could result in stack or buffer overflow on serialization.
 */
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
