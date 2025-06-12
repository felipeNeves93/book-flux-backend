package com.bookflux.repository;

import com.bookflux.repository.collection.book.UserBookCollection;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserBookRepository extends MongoRepository<UserBookCollection, String> {
    List<UserBookCollection> findByUserId(String userId);

    Optional<UserBookCollection> findByBookId(String bookId);


    Optional<UserBookCollection> findByUserIdAndBookId(String userId, String bookId);

}
