package edu.cnm.deepdive.abqtrailsserverside.model.dao;

import edu.cnm.deepdive.abqtrailsserverside.model.entity.Photo;
import edu.cnm.deepdive.abqtrailsserverside.model.entity.Trail;
import edu.cnm.deepdive.abqtrailsserverside.model.entity.User;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

/**
 *  Performs CRUD operations on {@link Photo} entity instances.
 */
public interface PhotoRepository extends CrudRepository<Photo, UUID> {

  List<Photo> getAllByTrailOrderByCreatedDesc (Trail trail);

  List<Photo> getAllByUserOrderByCreatedDesc (User user);

  List<Photo> getAllByOrderByCreatedDesc ();

  List<Photo> getAllById (UUID id);

}
