package com.bookflux.repository;

import com.bookflux.repository.collection.UserBookCollection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserBookRepository extends MongoRepository<UserBookCollection, String> {

}
