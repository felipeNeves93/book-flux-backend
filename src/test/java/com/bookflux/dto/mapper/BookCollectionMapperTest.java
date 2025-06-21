package com.bookflux.dto.mapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.bookflux.dto.BookCollectionDto;
import com.bookflux.dto.google.ImagelinksDto;
import com.bookflux.enums.MaturityRating;
import com.bookflux.repository.collection.book.BookCollection;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BookCollectionMapperTest {

  @DisplayName("When mapping from dto then should return collection")
  @Test
  void whenMappingFromDtoThenShouldReturnCollection() {
    var bookCollectionDto = BookCollectionDto.builder()
        .id("id")
        .title("title")
        .authors(List.of("authors"))
        .publisher("publisher")
        .publishedDate("publishedDate")
        .description("description")
        .pageCount(1)
        .categories(List.of("categories"))
        .maturityRating(MaturityRating.MATURE)
        .averageRating(1.0)
        .imageLinks(new ImagelinksDto("imageLinks", "link"))
        .language("language")
        .build();

    var mappedBookCollection = BookCollectionMapper.fromDto(bookCollectionDto);

    assertThat(mappedBookCollection.getId()).isEqualTo("id");
    assertThat(mappedBookCollection.getTitle()).isEqualTo("title");
    assertThat(mappedBookCollection.getAuthors()).isEqualTo(List.of("authors"));
    assertThat(mappedBookCollection.getPublisher()).isEqualTo("publisher");
    assertThat(mappedBookCollection.getPublishedDate()).isEqualTo("publishedDate");
    assertThat(mappedBookCollection.getDescription()).isEqualTo("description");
    assertThat(mappedBookCollection.getPageCount()).isEqualTo(1);
    assertThat(mappedBookCollection.getCategories()).isEqualTo(List.of("categories"));
    assertThat(mappedBookCollection.getMaturityRating()).isEqualTo(MaturityRating.MATURE);
    assertThat(mappedBookCollection.getAverageRating()).isEqualTo(1.0);
    assertThat(mappedBookCollection.getImageLinks().getSmallThumbnail()).isEqualTo("imageLinks");
    assertThat(mappedBookCollection.getImageLinks().getThumbnail()).isEqualTo("link");
    assertThat(mappedBookCollection.getLanguage()).isEqualTo("language");
  }

  @DisplayName("When mapping from collection then should return dto")
  @Test
  void whenMappingFromCollectionThenShouldReturnDto() {
    var bookCollection = BookCollection.builder()
        .id("id")
        .title("title")
        .authors(List.of("authors"))
        .publisher("publisher")
        .publishedDate("publishedDate")
        .description("description")
        .pageCount(1)
        .categories(List.of("categories"))
        .maturityRating(MaturityRating.MATURE)
        .averageRating(1.0)
        .imageLinks(new ImagelinksDto("imageLinks", "link"))
        .language("language")
        .build();

    var mappedBookCollectionDto = BookCollectionMapper.toDto(bookCollection);

    assertThat(mappedBookCollectionDto.getId()).isEqualTo("id");
    assertThat(mappedBookCollectionDto.getTitle()).isEqualTo("title");
    assertThat(mappedBookCollectionDto.getAuthors()).isEqualTo(List.of("authors"));
    assertThat(mappedBookCollectionDto.getPublisher()).isEqualTo("publisher");
    assertThat(mappedBookCollectionDto.getPublishedDate()).isEqualTo("publishedDate");
    assertThat(mappedBookCollectionDto.getDescription()).isEqualTo("description");
    assertThat(mappedBookCollectionDto.getPageCount()).isEqualTo(1);
    assertThat(mappedBookCollectionDto.getCategories()).isEqualTo(List.of("categories"));
    assertThat(mappedBookCollectionDto.getMaturityRating()).isEqualTo(MaturityRating.MATURE);
    assertThat(mappedBookCollectionDto.getAverageRating()).isEqualTo(1.0);
    assertThat(mappedBookCollectionDto.getImageLinks().getSmallThumbnail()).isEqualTo("imageLinks");
    assertThat(mappedBookCollectionDto.getImageLinks().getThumbnail()).isEqualTo("link");
    assertThat(mappedBookCollectionDto.getLanguage()).isEqualTo("language");
  }

  @DisplayName("When sending null values to map should throw exception")
  @Test
  void whenSendingNullValuesToMapShouldThrowException() {
    assertThatThrownBy(() -> BookCollectionMapper.fromDto(null))
        .isInstanceOf(NullPointerException.class);
  }

  @DisplayName("When sending null values to map collection should throw exception")
  @Test
  void whenSendingNullValuesToMapCollectionShouldThrowException() {
    assertThatThrownBy(() -> BookCollectionMapper.toDto(null))
        .isInstanceOf(NullPointerException.class);
  }

}
