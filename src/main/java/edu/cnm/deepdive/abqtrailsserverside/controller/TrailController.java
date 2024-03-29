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

import edu.cnm.deepdive.abqtrailsserverside.model.dao.TrailRepository;
import edu.cnm.deepdive.abqtrailsserverside.model.entity.Trail;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Defines REST endpoints for servicing requests on {@link Trail} resources,
 * invoking {@link TrailRepository} methods to perform the required operations.
 */
@RestController
@ExposesResourceFor(Trail.class)
@RequestMapping("trails")
public class TrailController {

  private TrailRepository repository;

  /**
   * Initializes this instance, injecting an instance of {@link TrailRepository}.
   *
   * @param repository repository used for operations on {@link Trail} entity instances.
   */
  public TrailController(TrailRepository repository) {
    this.repository = repository;
  }

  /**
   * Gets the specified trail.
   *
   * @param id for trail.
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Trail getTrail(@PathVariable UUID id) {
    return repository.findById(id).get();
  }

  /**
   * Returns list of trails.
   *
   * @param fragment ??
   */
  @GetMapping(value = "search", params = "nameFrag", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Trail> search(@RequestParam(value = "nameFrag", required = true) String fragment) {
    return repository.findAllByNameContainingOrderByCabqId(fragment);
  }

  /**
   * Gets the specified trail.
   *
   * @param cabqId id of trail.
   */
  @GetMapping(value = "search", params = "cabqId", produces = MediaType.APPLICATION_JSON_VALUE)
  public Trail get(@RequestParam(value = "cabqId", required = true) long cabqId) {
    return repository.findByCabqId(cabqId).get();
  }

  /**
   * Returns list of trails by CabqId.
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Trail> list() {
    return repository.getAllByOrderByCabqId();
  }

  /**
   * Maps (via annotation) a {@link NoSuchElementException} to a response status code of
   * {@link HttpStatus#NOT_FOUND}.
   */
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound() {
  }

}
