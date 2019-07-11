package edu.cnm.deepdive.abqtrailsserverside.model.entity;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.lang.NonNull;

public class User {

  private static EntityLinks entityLinks;

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "user_id", columnDefinition = "CHAR(16) FOR BIT DATA", nullable = false,
      updatable = false)
  private UUID id;

  @NonNull
  @Column(name = "first_name", nullable = false)
  private String firstName;

  @NonNull
  @Column(name = "last_name", nullable = false)
  private String lastName;

  @OneToMany(fetch = FetchType.LAZY,
      cascade = {CascadeType.ALL.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinTable //TODO Finish this
  //TODO Set as Flat?
  private List<User> users = new LinkedList<>();

  public UUID getId() {
    return id;
  }

  public List<User> getUsers() {
    return users;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  //  @Override TODO turn this on when if we create flat and implement. Otherwise, delete.
  public URI getHref() {
    return entityLinks.linkForSingleResource(User.class, id).toUri();
  }

  @PostConstruct
  private void init() {
    String ignore = entityLinks.toString();
  }

  @Autowired
  private void  setEntityLinks(EntityLinks entityLinks) {
    User.entityLinks = entityLinks;
  }

}
