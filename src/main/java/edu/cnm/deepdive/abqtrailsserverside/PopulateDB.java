package edu.cnm.deepdive.abqtrailsserverside;

import edu.cnm.deepdive.abqtrailsserverside.data.DataMapper;
import edu.cnm.deepdive.abqtrailsserverside.model.dao.TrailRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
//Comment @Profile out the first time you run the application. Then, stop, un-comment, and re-run.
@Profile("!default")
public class PopulateDB implements CommandLineRunner {

  //TODO Figure out why this runs everytime you run the serverside application and stop it.

  private TrailRepository trailRepository;

  public PopulateDB(TrailRepository trailRepository) {
    this.trailRepository = trailRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    DataMapper.mapTrail(DataMapper.getData(), trailRepository);
  }

}
