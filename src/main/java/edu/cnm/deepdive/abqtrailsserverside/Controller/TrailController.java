package edu.cnm.deepdive.abqtrailsserverside.Controller;

import edu.cnm.deepdive.abqtrailsserverside.model.entity.Trail;
import edu.cnm.deepdive.abqtrailsserverside.model.entity.User;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

public class TrailController {

  private TrailRepository repository;

  public TrailController(TrailRepository repository) {
    this.repository = repository;
  }

  @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Trail get(@PathVariable("id") UUID id) {
    return repository.findById(id).get();
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Trail> list() {
    return repository.getAllByOrderByName();
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Trail> list() {
    return repository.getAllByLengthOrderByName();
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Trail> list() {
    return repository.getAllByBikeOrderByName();
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Trail> list() {
    return repository.getAllByHorseOrderByName();
  }

  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound() {
  }

}
