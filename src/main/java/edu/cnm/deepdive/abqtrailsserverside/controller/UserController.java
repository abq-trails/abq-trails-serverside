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

import edu.cnm.deepdive.abqtrailsserverside.model.dao.UserRepository;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Defines REST endpoints for servicing requests on {@link User} resources, invoking
 * {@link UserRepository} methods to perform the required operations.
 */
@RestController
@ExposesResourceFor(User.class)
@RequestMapping("users")
public class UserController {

  private final UserRepository repository;

  /**
   * Initializes this instance, injecting an instance of {@link UserRepository}.
   *
   * @param repository repository used for operations on {@link User} entity instances.
   */
  public UserController(UserRepository repository) {
    this.repository = repository;
  }
  /**
   * Gets the specified user.
   * @param id for user.
   */
  @GetMapping(value = "{id}",produces = MediaType.APPLICATION_JSON_VALUE)
  public User get(@PathVariable("id") UUID id) {
    return repository.findById(id).get();
  }

  /**
   * Returns list of  all users by username.
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<User> list() {
    return repository.getAllByOrderByUsername();
  }

  /**
   * Adds the provided {@link User} resource to the database and returns the completed resource.
   * @param user {@link User} resource.
   * @return {@link User} resource.
   */
  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> post(@RequestBody User user) {
    repository.save(user);
    return ResponseEntity.created(user.getHref()).body(user);
  }

  /**
   * Deletes the specified {@link User} resource from the database.
   * @param id of user {@link User}.
   */
  @DeleteMapping(value = "{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") UUID id) {
    repository.delete(get(id));
  }

  /**
   * Maps (via annotation) a {@link NoSuchElementException} to a response status code
   * of {@link HttpStatus#NOT_FOUND}.
   */
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound() {
  }

}
