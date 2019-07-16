package edu.cnm.deepdive.abqtrailsserverside.controller;

import edu.cnm.deepdive.abqtrailsserverside.model.dao.RatingRepository;
import edu.cnm.deepdive.abqtrailsserverside.model.entity.Rating;
import edu.cnm.deepdive.abqtrailsserverside.model.entity.Trail;
import edu.cnm.deepdive.abqtrailsserverside.model.entity.User;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ExposesResourceFor(Rating.class)
@RequestMapping("ratings")
public class RatingController {

  private final RatingRepository repository;
  
  public RatingController(RatingRepository repository) {
    this.repository = repository;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Rating> list() {
    return repository.getAllByOrderByCreatedDesc();
  }

  @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Rating get(@PathVariable("id") UUID id) {
    return repository.findById(id).get();
  }

  @GetMapping(value = "search", params = "user",produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Rating> search(@RequestParam(value = "user", required = true) User user) {
    return repository.getAllByUserOrderByCreatedDesc(user);
  }

  @GetMapping(value = "search", params = "trail", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Rating> search(@RequestParam(value = "trail", required = true) Trail trail) {
    return repository.getAllByTrailOrderByCreatedDesc(trail);
  }

  //TODO Build this so that it returns username rather than uuid (see Genre I think).
  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Rating> post(@RequestBody Rating rating) {
    repository.save(rating);
    return ResponseEntity.created(rating.getHref()).body(rating);
  }

  @PutMapping(value = "{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Rating put(@PathVariable("id") UUID id, @RequestBody Rating rating) {
    Rating existingRating = repository.findById(id).get();
    existingRating.setReview(rating.getReview());
    existingRating.setRating(rating.getRating());
    existingRating.setTrail(rating.getTrail());
    existingRating.setUser(rating.getUser());
    repository.save(existingRating);
   //TODO Add in if statement to retrieve user etc. if necessary (see Movie Controller put).
    return existingRating;
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
