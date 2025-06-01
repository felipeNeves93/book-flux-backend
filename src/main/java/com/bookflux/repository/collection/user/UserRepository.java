package com.bookflux.repository.collection.user;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

  Optional<User> findByEmail(String email);

  Optional<User> findByUsername(String username);

}
