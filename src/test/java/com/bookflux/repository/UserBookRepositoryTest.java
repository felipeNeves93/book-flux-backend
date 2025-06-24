package com.bookflux.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.bookflux.MongoRepositoryTestContext;
import com.bookflux.repository.collection.book.UserBookCollection;
import com.bookflux.utils.StringUtils;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

class UserBookRepositoryTest extends MongoRepositoryTestContext {

  @Autowired
  private UserBookRepository userBookRepository;

  @Autowired
  private MongoTemplate mongoTemplate;

  @AfterEach
  void tearDown() {
    mongoTemplate.getDb().drop();
  }

  @DisplayName("When inserting user book collection then should return the saved user book")
  @Test
  void whenInsertingUserBookCollectionThenShouldReturnTheSavedUserBook() {
    var userbookCollection = this.createUserBookCollection();
    var expectedBookId = userbookCollection.getBookId();

    var savedUserBook = userBookRepository.save(userbookCollection);

    assertThat(savedUserBook.getBookId()).isEqualTo(expectedBookId);

    savedUserBook = userBookRepository.findById(savedUserBook.getId()).orElse(null);

    assertThat(savedUserBook).isNotNull();
    assertThat(savedUserBook.getBookId()).isEqualTo(expectedBookId);
    assertThat(savedUserBook.getTitle()).isEqualTo(userbookCollection.getTitle());
    assertThat(savedUserBook.getAuthors()).isEqualTo(userbookCollection.getAuthors());
    assertThat(savedUserBook.getDescription()).isEqualTo(userbookCollection.getDescription());
    assertThat(savedUserBook.getUserId()).isEqualTo(userbookCollection.getUserId());
    assertThat(savedUserBook.isFavorite()).isEqualTo(userbookCollection.isFavorite());
    assertThat(savedUserBook.isRead()).isEqualTo(userbookCollection.isRead());
    assertThat(savedUserBook.getStars()).isEqualTo(userbookCollection.getStars());

  }

  @DisplayName("When deleting user book collection then should not return it when searching for id")
  @Test
  void whenDeletingUserBookCollectionThenShouldNotReturnItWhenSearchingForIt() {

    var userBookCollection = this.createUserBookCollection();
    var savedBook = userBookRepository.save(userBookCollection);

    savedBook = userBookRepository.findById(savedBook.getId()).orElse(null);

    assertThat(savedBook).isNotNull();

    userBookRepository.deleteById(savedBook.getBookId());

    savedBook = userBookRepository.findById(userBookCollection.getBookId()).orElse(null);

    assertThat(savedBook).isNull();
  }

  private UserBookCollection createUserBookCollection() {
    var bookCollection = new UserBookCollection();
    bookCollection.setBookId(StringUtils.generateUUID());
    bookCollection.setTitle("title");
    bookCollection.setAuthors(List.of("author"));
    bookCollection.setDescription("description");
    bookCollection.setUserId("userId");
    bookCollection.setStars(5);
    bookCollection.setFavorite(true);
    bookCollection.setRead(true);

    return userBookRepository.save(bookCollection);
  }


}
