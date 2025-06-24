package com.bookflux.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.bookflux.DummyObjectFactory;
import com.bookflux.integration.service.BookCollectionApiService;
import com.bookflux.repository.BookRepository;
import com.bookflux.service.book.BookCollectionService;
import com.bookflux.service.book.BookCollectionServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BookServiceTest {

  private BookCollectionApiService bookCollectionApiService;

  private BookRepository bookRepository;

  private BookCollectionService bookCollectionService;

  @BeforeEach
  void setup() {
    this.bookCollectionApiService = mock(BookCollectionApiService.class);
    this.bookRepository = mock(BookRepository.class);

    this.bookCollectionService = new BookCollectionServiceImpl(bookRepository,
        bookCollectionApiService);
  }

  @DisplayName("When searching by title externally and it exists,then should return it")
  @Test
  void whenSearchingByTitleExternallyAndItExistsThenShouldReturnIt() {
    final var expectedTitle = "The Great Novel";

    when(bookCollectionApiService.searchBook(anyString())).thenReturn(Optional.of(
        DummyObjectFactory.createDummyGoogleBooksResponseDto()));

    var bookCollection = bookCollectionService.findByTitleExternally("test");

    assertThat(bookCollection).isNotEmpty();
    assertThat(bookCollection.getFirst().getTitle()).isEqualTo(expectedTitle);
  }

  @DisplayName("When searching by title externally and it does notexist,then should return empty")
  @Test
  void whenSearchingByTitleExternallyAndItDoesNotExistThenShouldReturnEmpty() {
    when(bookCollectionApiService.searchBook(anyString())).thenReturn(Optional.empty());

    var bookCollection = bookCollectionService.findByTitleExternally("test");

    assertThat(bookCollection).isEmpty();
  }
}
