package com.bookflux.dto;

import com.bookflux.enums.MaturityRating;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleBooksResponseDto {
    private List<Item> items;

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {
        private VolumeInfo volumeInfo;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VolumeInfo {
        private String title;
        private List<String> authors;
        private String publisher;
        private String publishedDate;
        private String description;
        private Integer pageCount;
        private List<String> categories;
        private String maturityRating;
        private Double averageRating;
        private ImagelinksDto imageLinks;
        private String language;
    }
}