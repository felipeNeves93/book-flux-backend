package com.bookflux.aplication.mapper;


import com.bookflux.dto.GoogleBooksResponseDto;
import com.bookflux.dto.ImagelinksDto;
import com.bookflux.enums.MaturityRating;
import com.bookflux.repository.collection.BookCollection;

import java.util.Collections;

public final class BookMapper {

    public static BookCollection toDomain(GoogleBooksResponseDto responseDto) {
        if (responseDto == null || responseDto.getItems() == null || responseDto.getItems().isEmpty()) {
            return BookCollection.builder()
                    .title("Book not found")
                    .authors(Collections.emptyList())
                    .build();
        }

        GoogleBooksResponseDto.VolumeInfo info = responseDto.getItems().get(0).getVolumeInfo();

        return BookCollection.builder()
                .id(null)
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