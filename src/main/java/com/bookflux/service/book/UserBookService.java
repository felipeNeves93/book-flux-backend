package com.bookflux.service.book;

import com.bookflux.repository.collection.book.BookCollection;
import com.bookflux.repository.collection.book.UserBookCollection;
import java.util.Optional;

public interface UserBookService {

  UserBookCollection addBookToCollection(BookCollection bookCollection);

  Optional<UserBookCollection> findById(String userBookId);

  UserBookCollection save(UserBookCollection userBookCollection);

  void remove(String userBookId);

  void updateFavorite(String userBookId, boolean isFavorite);

  void evaluateBook(String userBookId, Integer stars);

  void completeRead(String userBookId, boolean isRead);

}
