package edu.cnm.deepdive.abqtrailsserverside.model.entity;

import java.net.URI;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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

public class Rating {

  private static EntityLinks entityLinks;

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "rating_id", columnDefinition = "CHAR(16) FOR BIT DATA", nullable = false,
      updatable = false)
  private UUID id;

  @OneToMany(fetch = FetchType.LAZY,
      cascade = {CascadeType.ALL.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
          CascadeType.REFRESH})
  @JoinTable(joinColumns = @JoinColumn(name = "rating_id"),
      inverseJoinColumns = @JoinColumn(name = "trail_id"))
  @OrderBy("trail_name asc")
  private List<Trail> trails = new LinkedList<>();

  @ManyToOne(fetch = FetchType.LAZY,
      cascade = {CascadeType.ALL.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
          CascadeType.REFRESH})
  @JoinTable(joinColumns = @JoinColumn(name = "rating_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  @OrderBy("user_name asc")
  private List<User> users = new LinkedList<>();
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

  public UUID getId() {
    return id;
  }

  public List<Trail> getTrails() {
    return trails;
  }

  public List<User> getUsers() {
    return users;
  }

  public Date getCreated() {
    return created;
  }

  public Date getUpdated() {
    return updated;
  }

  public URI getHref() {
    return entityLinks.linkForSingleResource(Rating.class, id).toUri();
  }

  @PostConstruct
  private void init() {
    String ignore = entityLinks.toString();
  }

  @Autowired
  private void setEntityLinks(EntityLinks entityLinks) {
    Rating.entityLinks = entityLinks;
  }

}
