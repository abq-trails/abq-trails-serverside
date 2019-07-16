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

@RestController
@RequestMapping("photos")
@ExposesResourceFor(Photo.class)
public class PhotoController {

  private final PhotoRepository repository;

  public PhotoController(PhotoRepository repository) {
    this.repository = repository;
  }

  @GetMapping(value = "search", params = "user", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Photo> search(@RequestParam(value = "user", required = true) Trail trailName) {
    return repository.getAllByTrailOrderByCreatedDesc(trailName);
  }

  @GetMapping(value = "search", params = "trail", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Photo> search(@RequestParam(value = "trail", required = true) User userName) {
    return repository.getAllByUserOrderByCreatedDesc(userName);
  }

  @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Photo get(@PathVariable("id") UUID id) {
    return repository.findById(id).get();
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Photo> post(@RequestBody Photo photo) {
    repository.save(photo);
    return ResponseEntity.created(photo.getHref()).body(photo);
  }

  @DeleteMapping(value = "{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") UUID id) {
    repository.delete(get(id));
  }

  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound() {}
}
