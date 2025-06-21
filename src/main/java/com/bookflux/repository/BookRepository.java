package com.bookflux.repository;

import com.bookflux.repository.collection.book.BookCollection;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<BookCollection, String> {

  List<BookCollection> findByTitleContainingIgnoreCase(String title);

}
