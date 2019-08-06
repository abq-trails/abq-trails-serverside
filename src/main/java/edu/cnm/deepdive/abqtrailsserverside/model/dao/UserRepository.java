package edu.cnm.deepdive.abqtrailsserverside.model.dao;

import edu.cnm.deepdive.abqtrailsserverside.model.entity.User;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

/**
 * Performs CRUD operations on {@link User} entity instances.
 */
public interface UserRepository extends CrudRepository<User, UUID> {

  List<User> getAllByOrderByUsername ();

}

