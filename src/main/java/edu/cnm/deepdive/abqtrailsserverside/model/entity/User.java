/*
 * Copyright 2019 Denelle Britton Linebarger, Alana Chigbrow, Anita Martin, David Nelson
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package edu.cnm.deepdive.abqtrailsserverside.model.entity;

import edu.cnm.deepdive.abqtrailsserverside.view.FlatUser;
import java.net.URI;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * Returns and sets data for <code>User</code> entity.
 */
@Entity(name = "user_profile")
@Component
public class User implements FlatUser {

  private static EntityLinks entityLinks;

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "user_id", columnDefinition = "BINARY(16)",
      nullable = false, updatable = false)
//  @Column(name = "user_id", columnDefinition = "CHAR(16) FOR BIT DATA",
//      nullable = false, updatable = false)
  private UUID id;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created;

  @NonNull
  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false)
  private Date updated;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false, unique = true)
  private String authenticatedId;

  @NonNull
  @Column(name = "first_name", nullable = false)
  private String firstName;

  @NonNull
  @Column(name = "last_name", nullable = false)
  private String lastName;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  private List<Photo> photos = new LinkedList<>();

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  private List<Review> reviews = new LinkedList<>();


  @Override
  public UUID getId() {
    return id;
  }

  @Override
  public Date getCreated() {
    return created;
  }

  @Override
  public Date getUpdated() {
    return updated;
  }

  /**
   * Returns list of photos for this instance.
   */
  public List<Photo> getPhotos() {
    return photos;
  }

  /**
   * Returns list of reviews for this instance.
   */
  public List<Review> getReviews() {
    return reviews;
  }

  @Override
  public String getUsername() {
    return username;
  }

  /**
   * Sets username for this instance.
   */
  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String getAuthenticatedId() {
    return authenticatedId;
  }

  /**
   * Sets authenticatedId for this instance.
   */
  public void setAuthenticatedId(String authenticatedId) {
    this.authenticatedId = authenticatedId;
  }

  @Override
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets firstName for this instance.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Override
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets lastName for this instance.
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public URI getHref() {
    return entityLinks.linkForSingleResource(User.class, id).toUri();
  }

  @PostConstruct
  private void init() {
    String ignore = entityLinks.toString();
  }

  @Autowired
  private void setEntityLinks(EntityLinks entityLinks) {
    User.entityLinks = entityLinks;
  }

}
