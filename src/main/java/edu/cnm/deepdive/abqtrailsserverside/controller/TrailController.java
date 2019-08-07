package edu.cnm.deepdive.abqtrailsserverside.controller;

import edu.cnm.deepdive.abqtrailsserverside.model.dao.TrailRepository;
import edu.cnm.deepdive.abqtrailsserverside.model.entity.Trail;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ExposesResourceFor(Trail.class)
@RequestMapping("trails")
public class TrailController {

  private TrailRepository repository;

  public TrailController(TrailRepository repository) {
    this.repository = repository;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Trail getTrail(@PathVariable UUID id) {
    return repository.findById(id).get();
  }

  @GetMapping(value = "search", params = "nameFrag", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Trail> search(@RequestParam(value = "nameFrag", required = true) String fragment) {
    return repository.findAllByNameContainingOrderByCabqId(fragment);
  }

  @GetMapping(value = "search", params = "cabqId", produces = MediaType.APPLICATION_JSON_VALUE)
  public Trail get(@RequestParam(value = "cabqId", required = true) long cabqId) {
    return repository.findByCabqId(cabqId).get();
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Trail> list() {
    return repository.getAllByOrderByCabqId();
  }


  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound() {
  }

}
