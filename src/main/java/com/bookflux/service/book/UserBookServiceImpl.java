package com.bookflux.service.book;

import com.bookflux.integration.service.BookCollectionApiService;
import com.bookflux.integration.service.ReadingSessionService;
import com.bookflux.repository.UserBookRepository;
import com.bookflux.repository.collection.book.BookCollection;
import com.bookflux.repository.collection.book.UserBookCollection;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserBookServiceImpl implements UserBookService {

  private final UserBookRepository userBookRepository;
  private final ReadingSessionService readingSessionService;
  private final BookCollectionApiService bookCollectionApiService;

  @Override
  public UserBookCollection addBookToCollection(BookCollection bookCollection) {

    Objects.requireNonNull(bookCollection, "BookCollection cannot be null");

    var userBook = UserBookCollection.fromBookCollection(bookCollection);

    log.debug("Adding book to collection {}", userBook);
    return userBookRepository.save(userBook);
  }

  @Override
  public Optional<UserBookCollection> findById(String userBookId) {
    return userBookRepository.findById(userBookId);
  }

  @Override
  public UserBookCollection save(UserBookCollection userBookCollection) {
    return userBookRepository.save(userBookCollection);
  }

  @Override
  public void remove(String userBookId) {

  }

  @Override
  public void updateFavorite(String userBookId, boolean isFavorite) {

  }

  @Override
  public void evaluateBook(String userBookId, Integer stars) {

  }

  @Override
  public void completeRead(String userBookId, boolean isRead) {

  }
}
