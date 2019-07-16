package edu.cnm.deepdive.abqtrailsserverside.model.dao;

import edu.cnm.deepdive.abqtrailsserverside.model.entity.Trail;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface TrailRepository extends CrudRepository<Trail, UUID> {

  List<Trail> getAllByNameOrderAsc (Trail trail);
  List<Trail> getAllByLengthOrderByNameAsc (Trail trail);
  List<Trail> getAllByHorseOrderByNameAsc (Trail trail);
  List<Trail> getAllByBikeOrderByNameAsc (Trail trail);
  List<Trail> getAllByDogOrderByNameAsc (Trail trail);
  List<Trail> getAllById (Trail trail);




}

