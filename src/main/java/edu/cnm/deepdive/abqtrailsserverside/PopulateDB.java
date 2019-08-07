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
package edu.cnm.deepdive.abqtrailsserverside;

import edu.cnm.deepdive.abqtrailsserverside.data.DataMapper;
import edu.cnm.deepdive.abqtrailsserverside.model.dao.TrailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Runs the class that pulls from the Cabq Data source and populates the trail table.
 */
@SpringBootApplication
public class PopulateDB implements CommandLineRunner {

  //TODO Figure out why this runs everytime you run the serverside application and stop it.

  @Autowired
  private TrailRepository trailRepository;

  /**
   * Facilitates communication between the entity and the RoomDatabase.
   */
  public static void main(String[] args) {
    SpringApplication.run(PopulateDB.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    DataMapper.mapTrail(DataMapper.getData(), trailRepository);
  }
}
