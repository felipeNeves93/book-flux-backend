package com.bookflux.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.bookflux.DummyObjectFactory;
import com.bookflux.exception.ObjectNotFoundException;
import com.bookflux.integration.service.ReadingSessionService;
import com.bookflux.repository.UserBookRepository;
import com.bookflux.service.book.UserBookService;
import com.bookflux.service.book.UserBookServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserBookServiceTest {

  private UserBookRepository userBookRepository;
  private ReadingSessionService readingSessionService;
  private UserBookService userBookService;

  @BeforeEach
  void setup() {
    userBookRepository = mock(UserBookRepository.class);
    readingSessionService = mock(ReadingSessionService.class);
    userBookService = new UserBookServiceImpl(userBookRepository, readingSessionService);
  }

  @DisplayName("Should throw null pointer when adding null book to collection")
  @Test
  void shouldThrowNullPointerWhenAddingNullBookToCollection() {
    assertThrows(NullPointerException.class, () -> userBookService.addBookToCollection(null));
  }

  @DisplayName("Should throw object not found when setting book as favorite and passing a missing id")
  @Test
  void shouldThrowObjectNotFoundWhenSettingBookAsFavoriteAndPassingAMissingId() {
    when(userBookRepository.findById(anyString())).thenReturn(Optional.empty());
    assertThrows(ObjectNotFoundException.class, () -> userBookService.updateFavorite("id", true));
  }

  @DisplayName("Should throw object not found when evaluating book and passing a missing id")
  @Test
  void shouldThrowObjectNotFoundWhenEvaluatingBookAndPassingAMissingId() {
    when(userBookRepository.findById(anyString())).thenReturn(Optional.empty());
    assertThrows(ObjectNotFoundException.class, () -> userBookService.evaluateBook("id", 4));
  }

  @DisplayName("Should throw illegalState when evaluating book and passing an invalid number of stars")
  @Test
  void shouldThrowIllegalStateExceptionWhenEvaluatingBookAndPassingAnInvalidNumberOfStars() {
    when(userBookRepository.findById(anyString())).thenReturn(
        Optional.of(DummyObjectFactory.createDummyUserBookCollection()));
    assertThrows(IllegalStateException.class, () -> userBookService.evaluateBook("id", 50));
  }


  @DisplayName("Should throw object not found when completing a read and passing a missing id")
  @Test
  void shouldThrowObjectNotFoundWhenSettingBookAsFavorite() {
    when(userBookRepository.findById(anyString())).thenReturn(Optional.empty());
    assertThrows(ObjectNotFoundException.class, () -> userBookService.completeRead("id", true));
  }


}
