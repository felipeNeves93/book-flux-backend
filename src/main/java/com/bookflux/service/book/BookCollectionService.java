package com.bookflux.service.book;

import com.bookflux.repository.collection.book.BookCollection;
import java.util.List;
import java.util.Optional;

public interface BookCollectionService {

  BookCollection save(BookCollection bookCollection);

  List<BookCollection> findByTitle(String title);

  List<BookCollection> findByTitleExternally(String title);

  Optional<BookCollection> findById(String bookId);


}
