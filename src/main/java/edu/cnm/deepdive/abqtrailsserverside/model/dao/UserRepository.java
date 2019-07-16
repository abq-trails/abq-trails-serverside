package edu.cnm.deepdive.abqtrailsserverside.model.dao;

import edu.cnm.deepdive.abqtrailsserverside.model.entity.User;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, UUID> {

  List<User> getAllByUserOrderByUsername (User user);

  List<User> getAllById (User user);


}


//List<> getAllByUserOrderByCreatedDesc (User user);
//List<> getAllById (Rating rating);
//List<Rating> getAllByUserOrderByCreatedDesc (User user);
//List<> getAllById ();