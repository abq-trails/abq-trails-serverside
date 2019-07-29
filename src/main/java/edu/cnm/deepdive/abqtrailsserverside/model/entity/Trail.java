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
import org.geojson.GeoJsonObject;
import org.geojson.Geometry;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Entity
@Component
public class Trail implements FlatTrail {

  private static EntityLinks entityLinks;

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "trail_id", columnDefinition = "CHAR(16) FOR BIT DATA", nullable = false,
      updatable = false)
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
  @Column(name = "cabq_id", nullable = false, unique = true)
  private long cabqId;

  @NonNull
  @Column(name = "trail_name", nullable = false, unique = true)
  private String name;

  @Column
  private GeoJsonObject coordinates;

  @Column
  private double length;

  @Column
  private boolean horse;

  @Column
  private boolean dog;

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

  public List<Photo> getPhotos() {
    return photos;
  }

  public List<Review> getReviews() {
    return reviews;
  }

  public long getCabqId() {
    return cabqId;
  }

  public void setCabqId(long cabqId) {
    this.cabqId = cabqId;
  }

  @Override
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public GeoJsonObject getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(GeoJsonObject coordinates) {
    this.coordinates = coordinates;
  }

  @Override
  public double getLength() {
    return length;
  }

  public void setLength(double length) {
    this.length = length;
  }

  @Override
  public boolean isHorse() {
    return horse;
  }

  public void setHorse(boolean horse) {
    this.horse = horse;
  }

  @Override
  public boolean isDog() {
    return dog;
  }

  public void setDog(boolean dog) {
    this.dog = dog;
  }

  @Override
  public boolean isBike() {
    return bike;
  }

  @Override
  public String getTrailHead() {
    return null;
  }

  public void setBike(boolean bike) {
    this.bike = bike;
  }

  @Override
  public double getRating() {
    return rating;
  }

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
