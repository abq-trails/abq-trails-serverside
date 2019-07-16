package edu.cnm.deepdive.abqtrailsserverside.Controller;

import edu.cnm.deepdive.abqtrailsserverside.model.dao.PhotoRepository;
import edu.cnm.deepdive.abqtrailsserverside.model.dao.UserRepository;
import edu.cnm.deepdive.abqtrailsserverside.model.entity.Photo;
import edu.cnm.deepdive.abqtrailsserverside.model.entity.Rating;
import edu.cnm.deepdive.abqtrailsserverside.model.entity.User;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserController {

  private final UserRepository repository;
  private final PhotoRepository photoRepository;
  private final RatingRepository ratingRepository;
  

  public UserController(UserRepository repository,
      PhotoRepository photoRepository,
      RatingRepository ratingRespository) {
    this.repository = repository;
    this.photoRepository = photoRepository;
    this.ratingRepository = ratingRespository;
  }

  @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public User get(@PathVariable("id") UUID id) {
    return repository.findById(id).get();
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<User> list() {
    return repository.getAllByOrderByUsername();
  }

  @PutMapping(value = "{userID}", produces = MediaType.APPLICATION_JSON_VALUE)
  public User attach(@PathVariable("userId") UUID userId, @PathVariable("ratingId") UUID ratingId,
      @PathVariable("photoId") UUID photoId){
    User user = get(userId);
    Rating rating = ratingRepository.findById(ratingId).get();
    if (!user.getRatings().contains(rating)) {
      user.getRatings().add(rating);
    }
    Photo photo = photoRepository.findById(photoId).get();
    if (!user.getPhotos().contains(photo)) {
      user.getPhotos().add(photo);
    }
    return repository.save(user);
  }

  @DeleteMapping(value = "{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") UUID id) {
    repository.delete(get(id));
  }

  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound() {
  }

}
