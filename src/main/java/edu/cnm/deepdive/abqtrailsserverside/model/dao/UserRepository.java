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

package edu.cnm.deepdive.abqtrailsserverside.model.dao;

import edu.cnm.deepdive.abqtrailsserverside.model.entity.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

/**
 * Performs CRUD operations on {@link User} entity instances.
 */
public interface UserRepository extends CrudRepository<User, UUID> {

  List<User> getAllByOrderByUsername ();
  Optional<User> getByUsername (String username);

}