package com.bookflux.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.bookflux.MongoRepositoryTestContext;
import com.bookflux.dto.ImagelinksDto;
import com.bookflux.enums.MaturityRating;
import com.bookflux.repository.collection.BookCollection;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

class BookRepositoryTest extends MongoRepositoryTestContext {

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private MongoTemplate mongoTemplate;

  @AfterEach
  void tearDown() {
    mongoTemplate.getDb().drop();
  }

  @DisplayName("When inserting book then should return after searching by id")
  @Test
  void whenInsertingBookThenShouldReturnAfterSearchingById() {
    var bookCollection = this.createBookCollection();
    bookCollection = bookRepository.save(bookCollection);

    var searchedBook = bookRepository.findById(bookCollection.getId());
    assertThat(searchedBook).isPresent();

    var extractedBook = searchedBook.get();

    assertThat(extractedBook.getAuthors().getFirst())
        .isEqualTo(bookCollection.getAuthors().getFirst());

    assertThat(extractedBook.getAverageRating()).isEqualTo(bookCollection.getAverageRating());
    assertThat(extractedBook.getCategories().getFirst()).isEqualTo(
        bookCollection.getCategories().getFirst());
    assertThat(extractedBook.getDescription()).isEqualTo(bookCollection.getDescription());
    assertThat(extractedBook.getTitle()).isEqualTo(bookCollection.getTitle());
    assertThat(extractedBook.getImageLinks().getSmallThumbnail())
        .isEqualTo(bookCollection.getImageLinks().getSmallThumbnail());
    assertThat(extractedBook.getImageLinks().getThumbnail())
        .isEqualTo(bookCollection.getImageLinks().getThumbnail());
    assertThat(extractedBook.getLanguage()).isEqualTo(bookCollection.getLanguage());
    assertThat(extractedBook.getPageCount()).isEqualTo(bookCollection.getPageCount());
    assertThat(extractedBook.getPublishedDate()).isEqualTo(bookCollection.getPublishedDate());
    assertThat(extractedBook.getMaturityRating()).isEqualTo(bookCollection.getMaturityRating());
    assertThat(extractedBook.getPublisher()).isEqualTo(bookCollection.getPublisher());
  }

  @DisplayName("When deleting existing book should not return it when searching for id")
  @Test
  void whenDeletingExistingBookShouldNotReturnItWhenSearchingForId() {
    var bookCollection = this.createBookCollection();
    bookCollection = bookRepository.save(bookCollection);

    bookRepository.deleteById(bookCollection.getId());

    var searchedBook = bookRepository.findById(bookCollection.getId());
    assertThat(searchedBook).isNotPresent();
  }

  @DisplayName("When updating existing book should return the updated book")
  @Test
  void whenUpdatingExistingBookShouldReturnTheUpdatedBook() {
    final var updatedDescription = "This is an updated description";

    var bookCollection = this.createBookCollection();

    var savedBook = bookRepository.save(bookCollection);
    savedBook.setDescription(updatedDescription);

    bookRepository.save(savedBook);

    var updatedBook = bookRepository.findById(savedBook.getId());

    assertThat(updatedBook).isPresent();
    assertThat(updatedBook.get().getDescription()).isEqualTo(updatedDescription);
  }

  private BookCollection createBookCollection() {
    return BookCollection.builder()
        .authors(List.of("Tolkien"))
        .averageRating(5.0)
        .categories(List.of("Fantasy"))
        .description("An amazing adventure to throw the ring into Mount Doom")
        .title("Lord of the Rings - The Return of the King")
        .imageLinks(new ImagelinksDto("https://example.com", "https://example.com"))
        .language("English")
        .pageCount(1000)
        .publishedDate("2022-01-01")
        .maturityRating(MaturityRating.NOT_MATURE)
        .publisher("Penguin")
        .build();
  }

}
