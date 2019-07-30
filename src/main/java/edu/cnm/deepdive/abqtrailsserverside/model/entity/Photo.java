package edu.cnm.deepdive.abqtrailsserverside.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.cnm.deepdive.abqtrailsserverside.view.FlatTrail;
import edu.cnm.deepdive.abqtrailsserverside.view.FlatUser;
import java.net.URI;
import java.net.URL;
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Entity
@Component
public class Photo {

  private static EntityLinks entityLinks;

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "photo_id", columnDefinition = "CHAR(16) FOR BIT DATA", nullable = false,
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

  @ManyToOne(fetch = FetchType.EAGER)
  @JsonSerialize(as = FlatTrail.class)
  @JoinColumn(name = "cabq_id")
  private Trail trail;

  @ManyToOne(fetch = FetchType.EAGER)
  @JsonSerialize(as = FlatUser.class)
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "photo_link")
  private URL photoLink;


  public UUID getId() {
    return id;
  }

  public Date getCreated() {
    return created;
  }

  public Date getUpdated() {
    return updated;
  }

  public Trail getTrail() {
    return trail;
  }

  public void setTrail(Trail trail) {
    this.trail = trail;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public URL getPhotoLink() {
    return photoLink;
  }

  public void setPhotoLink(URL photoLink) {
    this.photoLink = photoLink;
  }

  public URI getHref() {
    return entityLinks.linkForSingleResource(Photo.class, id).toUri();
  }

  @PostConstruct
  private void init() {
    String ignore = entityLinks.toString();
  }

  @Autowired
  private void  setEntityLinks(EntityLinks entityLinks) {
    Photo.entityLinks = entityLinks;
  }

}
