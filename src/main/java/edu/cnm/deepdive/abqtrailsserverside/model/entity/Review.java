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

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.cnm.deepdive.abqtrailsserverside.view.FlatTrail;
import edu.cnm.deepdive.abqtrailsserverside.view.FlatUser;
import java.net.URI;
import java.util.Date;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * Returns and sets data for <code>Review</code> entity.
 */
@Entity
@Component
public class Review {

  private static EntityLinks entityLinks;

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "rating_id", columnDefinition = "BINARY(16)", nullable = false,
      updatable = false)
//  @Column(name = "rating_id", columnDefinition = "CHAR(16) FOR BIT DATA", nullable = false,
//      updatable = false)
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

  @ManyToOne(fetch = FetchType.EAGER)
  @JsonSerialize(as = FlatTrail.class)
  @JoinColumn(name = "trail_id")
  private Trail trail;

  @ManyToOne(fetch = FetchType.EAGER)
  @JsonSerialize(as = FlatUser.class)
  @JoinColumn(name = "user_id")
  private User user;

  @NonNull
  @Column(nullable = false)
  private int rating;

  private String review;

  /**
   * Returns id for this instance.
   */
  public UUID getId() {
    return id;
  }

  /**
   * Returns created for this instance.
   */
  public Date getCreated() {
    return created;
  }

  /**
   * Returns updated for this instance.
   */
  public Date getUpdated() {
    return updated;
  }

  /**
   * Returns trail for this instance.
   */
  public Trail getTrail() {
    return trail;
  }

  /**
   * Sets trail for this instance.
   */
  public void setTrail(Trail trail) {
    this.trail = trail;
  }

  /**
   * Returns user for this instance.
   */
  public User getUser() {
    return user;
  }

  /**
   * Sets user for this instance.
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * Returns rating for this instance.
   */
  public int getRating() {
    return rating;
  }

  /**
   * Sets rating for this instance.
   */
  public void setRating(int rating) {
    this.rating = rating;
  }

  /**
   * Returns review for this instance.
   */
  public String getReview() {
    return review;
  }

  /**
   * Sets review for this instance.
   */
  public void setReview(String review) {
    this.review = review;
  }

  /**
   * Returns Href for this instance.
   */
  public URI getHref() {
    return entityLinks.linkForSingleResource(Review.class, id).toUri();
  }

  @PostConstruct
  private void init() {
    String ignore = entityLinks.toString();
  }

  @Autowired
  private void setEntityLinks(EntityLinks entityLinks) {
    Review.entityLinks = entityLinks;
  }

}
