package edu.cnm.deepdive.abqtrailsserverside.model.entity;


import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.hateoas.EntityLinks;

public class Photo {

  private static EntityLinks entityLinks;

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "photo_id", columnDefinition = "CHAR(16) FOR BIT DATA", nullable = false,
      updatable = false)
  private UUID id;
}
