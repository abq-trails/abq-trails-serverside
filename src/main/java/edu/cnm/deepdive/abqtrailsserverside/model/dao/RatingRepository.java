package edu.cnm.deepdive.abqtrailsserverside.model.dao;

import edu.cnm.deepdive.abqtrailsserverside.model.entity.Rating;
import edu.cnm.deepdive.abqtrailsserverside.model.entity.Trail;
import edu.cnm.deepdive.abqtrailsserverside.model.entity.User;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Rating, UUID> {

  List<Rating> getAllByTrailOrderByCreatedDesc (Trail trail);

  List<Rating> getAllByUserOrderByCreatedDesc (User user);

  List<Rating> getAllByOrderByCreatedDesc ();

}
