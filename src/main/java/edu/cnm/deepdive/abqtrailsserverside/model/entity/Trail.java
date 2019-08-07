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

import edu.cnm.deepdive.abqtrailsserverside.view.FlatTrail;
import java.net.URI;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
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
 * Returns and sets data for <code>Trail</code> entity.
 */
@Entity(name = "trail_table")
@Component
public class Trail implements FlatTrail {

  private static EntityLinks entityLinks;

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "trail_id", columnDefinition = "BINARY(16)", nullable = false,
      updatable = false)
//  @Column(name = "trail_id", columnDefinition = "CHAR(16) FOR BIT DATA", nullable = false,
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

  @NonNull
  @Column(name = "cabq_id", nullable = false)
  private Long cabqId;

  @NonNull
  @Column(name = "trail_name", nullable = false)
  private String name;

  @Column(columnDefinition = "JSON")
  private String coordinates;

  @Column
  private double length;

  @Column
  private boolean horse;

  @Column
  private boolean bike;

  @Column(name = "trail_rating")
  private double rating;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "trail",
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  private List<Review> reviews = new LinkedList<>();

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "trail",
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  private List<Photo> photos = new LinkedList<>();

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

  /**
   * Returns cabqId for this instance.
   */
  @NonNull
  public Long getCabqId() {
    return cabqId;
  }

  /**
   * Sets cabqId for this instance.
   */
  public void setCabqId(@NonNull Long cabqId) {
    this.cabqId = cabqId;
  }

  @Override
  public String getName() {
    return name;
  }

  /**
   * Sets name for this instance.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns coordinates for this instance.
   */
  public String getCoordinates() {
    return coordinates;
  }

  /**
   * Sets coordinates for this instance.
   */
  public void setCoordinates(String coordinates) {
    this.coordinates = coordinates;
  }

  @Override
  public double getLength() {
    return length;
  }

  /**
   * Sets length for this instance.
   */
  public void setLength(double length) {
    this.length = length;
  }

  @Override
  public boolean isHorse() {
    return horse;
  }

  /**
   * Sets horse flag for this instance.
   */
  public void setHorse(boolean horse) {
    this.horse = horse;
  }

  @Override
  public boolean isBike() {
    return bike;
  }

  /**
   * Sets bike flag for this instance.
   */
  public void setBike(boolean bike) {
    this.bike = bike;
  }

  @Override
  public double getRating() {
    return rating;
  }

  /**
   * Sets rating for this instance.
   */
  public void setRating(double rating) {
    this.rating = rating;
  }

  @Override
  public URI getHref() {
    return entityLinks.linkForSingleResource(Trail.class, id).toUri();
  }

  @PostConstruct
  private void init() {
    String ignore = entityLinks.toString();
  }

  @Autowired
  private void setEntityLinks(EntityLinks entityLinks) {
    Trail.entityLinks = entityLinks;
  }

}
