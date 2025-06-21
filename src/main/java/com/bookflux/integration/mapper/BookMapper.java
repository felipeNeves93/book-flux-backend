package com.bookflux.integration.mapper;


import com.bookflux.dto.google.GoogleBooksResponseDto;
import com.bookflux.dto.google.ImagelinksDto;
import com.bookflux.enums.MaturityRating;
import com.bookflux.repository.collection.book.BookCollection;

public final class BookMapper {

  private BookMapper() {

  }

  public static BookCollection toDomain(GoogleBooksResponseDto responseDto) {
    if (responseDto == null || responseDto.getItems() == null || responseDto.getItems().isEmpty()) {
      return null;
    }

    GoogleBooksResponseDto.VolumeInfo info = responseDto.getItems().getFirst().getVolumeInfo();

    return BookCollection.builder()
        .title(info.getTitle())
        .authors(info.getAuthors())
        .publisher(info.getPublisher())
        .publishedDate(info.getPublishedDate())
        .description(info.getDescription())
        .pageCount(info.getPageCount())
        .categories(info.getCategories())
        .maturityRating(convertMaturity(info.getMaturityRating()))
        .averageRating(info.getAverageRating())
        .imageLinks(mapImageLinks(info))
        .language(info.getLanguage())
        .build();
  }

  private static ImagelinksDto mapImageLinks(GoogleBooksResponseDto.VolumeInfo info) {
    if (info.getImageLinks() instanceof ImagelinksDto dto) {
      return dto;
    }
    return null;
  }

  private static MaturityRating convertMaturity(String value) {
    try {
      return MaturityRating.valueOf(value.toUpperCase());
    } catch (Exception e) {
      return null;
    }
  }
}