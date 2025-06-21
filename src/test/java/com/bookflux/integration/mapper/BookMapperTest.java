package com.bookflux.integration.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.bookflux.dto.google.GoogleBooksResponseDto;
import com.bookflux.dto.google.GoogleBooksResponseDto.VolumeInfo;
import com.bookflux.dto.google.ImagelinksDto;
import com.bookflux.enums.MaturityRating;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BookMapperTest {

  @DisplayName("When mapping book domain to dto should return mapped book")
  @Test
  void whenMappingBookDomainToDtoShouldReturnMappedBook() {

    var volumeInfo = getVolumeInfo();

    var item = new GoogleBooksResponseDto.Item();
    item.setVolumeInfo(volumeInfo);

    var responseDto = new GoogleBooksResponseDto();
    responseDto.setItems(List.of(item));

    var result = BookMapper.toDomain(responseDto);

    assertNotNull(result);
    assertEquals("Clean Code", result.getTitle());
    assertEquals(List.of("Robert C. Martin"), result.getAuthors());
    assertEquals("Prentice Hall", result.getPublisher());
    assertEquals("2008-08-01", result.getPublishedDate());
    assertEquals("A Handbook of Agile Software Craftsmanship", result.getDescription());
    assertEquals(464, result.getPageCount());
    assertEquals(List.of("Programming"), result.getCategories());
    assertEquals(MaturityRating.NOT_MATURE, result.getMaturityRating());
    assertEquals(4.7, result.getAverageRating());
    assertEquals("http://example.com/image.jpg", result.getImageLinks().getThumbnail());
    assertEquals("http://example.com/small.jpg", result.getImageLinks().getSmallThumbnail());
    assertEquals("en", result.getLanguage());
  }

  private static VolumeInfo getVolumeInfo() {
    var volumeInfo = new VolumeInfo();
    volumeInfo.setTitle("Clean Code");
    volumeInfo.setAuthors(List.of("Robert C. Martin"));
    volumeInfo.setPublisher("Prentice Hall");
    volumeInfo.setPublishedDate("2008-08-01");
    volumeInfo.setDescription("A Handbook of Agile Software Craftsmanship");
    volumeInfo.setPageCount(464);
    volumeInfo.setCategories(List.of("Programming"));
    volumeInfo.setMaturityRating("NOT_MATURE");
    volumeInfo.setAverageRating(4.7);
    volumeInfo.setImageLinks(new ImagelinksDto(
        "http://example.com/small.jpg",
        "http://example.com/image.jpg"
    ));
    volumeInfo.setLanguage("en");
    return volumeInfo;
  }

  @Test
  @DisplayName("when there is error mapping should return fallback book")
  void whenThereIsErrorMappingShouldReturnFallbackBook() {
    var emptyResponse = new GoogleBooksResponseDto();
    emptyResponse.setItems(List.of());

    var result = BookMapper.toDomain(emptyResponse);

    assertThat(result).isNull();
  }
}