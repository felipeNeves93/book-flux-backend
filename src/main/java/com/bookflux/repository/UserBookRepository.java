package com.bookflux.repository;

import com.bookflux.repository.collection.book.UserBookCollection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserBookRepository extends MongoRepository<UserBookCollection, String> {

}
