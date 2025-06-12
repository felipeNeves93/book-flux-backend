package com.bookflux.repository;

import com.bookflux.repository.collection.book.BookCollection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<BookCollection, String> {

}
