package edu.cnm.deepdive.abqtrailsserverside;

import edu.cnm.deepdive.abqtrailsserverside.data.DataMapper;
import edu.cnm.deepdive.abqtrailsserverside.model.dao.TrailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 */
@SpringBootApplication
public class PopulateDB implements CommandLineRunner {

  //TODO Figure out why this runs everytime you run the serverside application and stop it.

  @Autowired
  private TrailRepository trailRepository;

  @Override
  public void run(String... args) throws Exception {

    DataMapper.mapTrail(DataMapper.getData(), trailRepository);
  }

  /**
   *
   * @param args
   */
  public static void main(String[] args) {
    SpringApplication.run(PopulateDB.class, args);
  }
}
