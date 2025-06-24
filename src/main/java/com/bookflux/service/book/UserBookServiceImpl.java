package com.bookflux.service.book;

import com.bookflux.exception.ObjectNotFoundException;
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

  private static final String USER_BOOK_CLASS_NAME = "UserBook";

  private final UserBookRepository userBookRepository;
  private final ReadingSessionService readingSessionService;

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
    var userBook = userBookRepository.findById(userBookId)
        .orElseThrow(() -> new ObjectNotFoundException(userBookId, USER_BOOK_CLASS_NAME));

    userBook.setFavorite(isFavorite);

    userBookRepository.save(userBook);
  }

  @Override
  public void evaluateBook(String userBookId, Integer stars) {
    var userBook = userBookRepository.findById(userBookId)
        .orElseThrow(() -> new ObjectNotFoundException(userBookId, USER_BOOK_CLASS_NAME));

    if (this.isValidNumberOfStars(stars)) {
      userBook.setStars(stars);
      userBookRepository.save(userBook);
      return;
    }

    throw new IllegalStateException("Invalid amount of stars: " + stars);


  }

  @Override
  public void completeRead(String userBookId, boolean isRead) {
    var userBook = userBookRepository.findById(userBookId)
        .orElseThrow(() -> new ObjectNotFoundException(userBookId, USER_BOOK_CLASS_NAME));

    userBook.setRead(isRead);

    userBookRepository.save(userBook);
  }

  private boolean isValidNumberOfStars(Integer stars) {
    return stars > 0 && stars <= 5;
  }
}
