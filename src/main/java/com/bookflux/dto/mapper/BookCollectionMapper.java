package com.bookflux.dto.mapper;

import com.bookflux.dto.BookCollectionDto;
import com.bookflux.repository.collection.book.BookCollection;
import java.util.Objects;

public final class BookCollectionMapper {

  private BookCollectionMapper() {

  }

  public static BookCollection fromDto(BookCollectionDto dto) {
    Objects.requireNonNull(dto, "BookCollectionDto cannot be null");
    return BookCollection.builder()
        .id(dto.getId())
        .title(dto.getTitle())
        .authors(dto.getAuthors())
        .publisher(dto.getPublisher())
        .publishedDate(dto.getPublishedDate())
        .description(dto.getDescription())
        .pageCount(dto.getPageCount())
        .categories(dto.getCategories())
        .maturityRating(dto.getMaturityRating())
        .averageRating(dto.getAverageRating())
        .imageLinks(dto.getImageLinks())
        .language(dto.getLanguage())
        .build();
  }

  public static BookCollectionDto toDto(BookCollection entity) {
    Objects.requireNonNull(entity, "BookCollection cannot be null");
    return BookCollectionDto.builder()
        .id(entity.getId())
        .title(entity.getTitle())
        .authors(entity.getAuthors())
        .publisher(entity.getPublisher())
        .publishedDate(entity.getPublishedDate())
        .description(entity.getDescription())
        .pageCount(entity.getPageCount())
        .categories(entity.getCategories())
        .maturityRating(entity.getMaturityRating())
        .averageRating(entity.getAverageRating())
        .imageLinks(entity.getImageLinks())
        .language(entity.getLanguage())
        .build();
  }

}
