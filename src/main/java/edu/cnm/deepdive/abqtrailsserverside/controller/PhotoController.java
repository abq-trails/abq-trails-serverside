/*
 * Copyright 2019 Denelle Britton Linebarger, Alana Chigbrow, Anita Martin, David Nelson
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package edu.cnm.deepdive.abqtrailsserverside.controller;

import edu.cnm.deepdive.abqtrailsserverside.model.dao.PhotoRepository;
import edu.cnm.deepdive.abqtrailsserverside.model.entity.Photo;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Defines REST endpoints for servicing requests on {@link Photo} resources, invoking {@link
 * PhotoRepository} methods to perform the required operations.
 */
@RestController
@RequestMapping("photos")
@ExposesResourceFor(Photo.class)
public class PhotoController {

  private final PhotoRepository repository;

  /**
   * Initializes this instance, injecting an instance of {@link PhotoRepository}.
   *
   * @param repository repository used for operations on {@link Photo} entity instances.
   */
  public PhotoController(PhotoRepository repository) {
    this.repository = repository;
  }

  /**
   * Returns a list of all photos.
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Photo> list() {
    return repository.getAllByOrderByCreatedDesc();
  }

  /**
   * Returns a list of photos associated with specified trail.
   *
   * @param trailName name of trail.
   *
   * @return list of photos.
   */
  @GetMapping(value = "search", params = "trail", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Photo> search(@RequestParam(value = "trail", required = true) Trail trailName) {
    return repository.getAllByTrailOrderByCreatedDesc(trailName);
  }

  /**
   * Returns list of photos by username.
   *
   * @param userName username of user.
   *
   * @return list of photos by username.
   */
  @GetMapping(value = "search", params = "user", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Photo> search(@RequestParam(value = "user", required = true) User userName) {
    return repository.getAllByUserOrderByCreatedDesc(userName);
  }

  /**
   * Gets the specified photo.
   *
   * @param id for photo.
   */
  @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Photo get(@PathVariable("id") UUID id) {
    return repository.findById(id).get();
  }

  /**
   * Adds the provided {@link Photo} resource to the database and returns the completed resource.
   *
   * @param photo {@link Photo} resource.
   *
   * @return {@link Photo} resource
   */
  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Photo> post(@RequestBody Photo photo) {
    repository.save(photo);
    return ResponseEntity.created(photo.getHref()).body(photo);
  }

  /**
   * Deletes the specified {@link Photo} resource from the database.
   *
   * @param id photo {@link UUID}.
   */
  @DeleteMapping(value = "{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") UUID id) {
    repository.delete(get(id));
  }

  /**
   * Maps (via annotation) a {@link NoSuchElementException} to a response status code of {@link
   * HttpStatus#NOT_FOUND}.
   */
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound() {
  }
}
