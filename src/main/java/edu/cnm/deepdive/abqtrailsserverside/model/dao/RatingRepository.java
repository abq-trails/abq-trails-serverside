package edu.cnm.deepdive.abqtrailsserverside.model.dao;

import edu.cnm.deepdive.abqtrailsserverside.model.entity.Review;
import edu.cnm.deepdive.abqtrailsserverside.model.entity.Trail;
import edu.cnm.deepdive.abqtrailsserverside.model.entity.User;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Review, UUID> {

  List<Review> getAllByTrailOrderByCreatedDesc (Trail trail);

  List<Review> getAllByUserOrderByCreatedDesc (User user);

  List<Review> getAllByOrderByCreatedDesc ();

}
