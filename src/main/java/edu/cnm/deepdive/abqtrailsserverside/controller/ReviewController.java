//Copyright 2019 Denelle Britton Linebarger, Alana Chigbrow, Anita Martin, David Nelson
//
//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.

package edu.cnm.deepdive.abqtrailsserverside.controller;

import edu.cnm.deepdive.abqtrailsserverside.model.dao.ReviewRepository;
import edu.cnm.deepdive.abqtrailsserverside.model.entity.Review;
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

/**
 *
 */
@RestController
@ExposesResourceFor(Review.class)
@RequestMapping("ratings")
public class ReviewController {

  private final ReviewRepository repository;

  /**
   *
   */
  public ReviewController(ReviewRepository repository) {
    this.repository = repository;
  }

  /**
   *
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Review> list() {
    return repository.getAllByOrderByCreatedDesc();
  }

  /**
   *
   */
  @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Review get(@PathVariable("id") UUID id) {
    return repository.findById(id).get();
  }

  /**
   *
   */
  @GetMapping(value = "search", params = "user", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Review> search(@RequestParam(value = "user", required = true) User user) {
    return repository.getAllByUserOrderByCreatedDesc(user);
  }

  /**
   *
   */
  @GetMapping(value = "search", params = "trail", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Review> search(@RequestParam(value = "trail", required = true) Trail trail) {
    return repository.getAllByTrailOrderByCreatedDesc(trail);
  }

  /**
   *
   */
  //TODO Build this so that it returns username rather than uuid (see Genre I think).
  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Review> post(@RequestBody Review review) {
    repository.save(review);
    return ResponseEntity.created(review.getHref()).body(review);
  }

  /**
   *
   */
  @PutMapping(value = "{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Review put(@PathVariable("id") UUID id, @RequestBody Review review) {
    Review existingReview = repository.findById(id).get();
    existingReview.setReview(review.getReview());
    existingReview.setRating(review.getRating());
    existingReview.setTrail(review.getTrail());
    existingReview.setUser(review.getUser());
    repository.save(existingReview);
    //TODO Add in if statement to retrieve user etc. if necessary (see Movie Controller put).
    return existingReview;
  }

  /**
   *
   */
  @DeleteMapping(value = "{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") UUID id) {
    repository.delete(get(id));
  }

  /**
   *
   */
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound() {
  }

}
