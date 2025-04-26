package com.bookflux.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.bookflux.dto.GoogleBooksResponseDto;
import com.bookflux.dto.GoogleBooksResponseDto.VolumeInfo;
import com.bookflux.dto.ImagelinksDto;
import com.bookflux.enums.MaturityRating;
import com.bookflux.integration.service.BookCollectionApiService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class BookControllerTest {

  @Mock
  private BookCollectionApiService bookCollectionApiService;

  @InjectMocks
  private BookController bookController;

  public BookControllerTest() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("when searching book should return book collection")
  void whenSearchingBookShouldReturnBookCollection() {
    var query = "harry";

    var volumeInfo = getVolumeInfo();

    var item = new GoogleBooksResponseDto.Item();
    item.setVolumeInfo(volumeInfo);

    var responseDto = new GoogleBooksResponseDto();
    responseDto.setItems(List.of(item));

    when(bookCollectionApiService.searchBook(query)).thenReturn(responseDto);

    var result = bookController.searchBook(query).getBody();

    assertNotNull(result);
    assertEquals("Harry Potter", result.getTitle());
    assertEquals(List.of("J.K. Rowling"), result.getAuthors());
    assertEquals("Bloomsbury", result.getPublisher());
    assertEquals(MaturityRating.NOT_MATURE, result.getMaturityRating());
    assertEquals("http://example.com/image.jpg", result.getImageLinks().getThumbnail());
  }

  private static VolumeInfo getVolumeInfo() {
    var volumeInfo = new VolumeInfo();

    volumeInfo.setTitle("Harry Potter");
    volumeInfo.setAuthors(List.of("J.K. Rowling"));
    volumeInfo.setPublisher("Bloomsbury");
    volumeInfo.setPublishedDate("1997");
    volumeInfo.setDescription("A wizard boy...");
    volumeInfo.setPageCount(320);
    volumeInfo.setCategories(List.of("Fantasy"));
    volumeInfo.setMaturityRating("NOT_MATURE");
    volumeInfo.setAverageRating(4.5);
    volumeInfo.setImageLinks(
        new ImagelinksDto("http://example.com/image.jpg", "http://example.com/image.jpg"));
    volumeInfo.setLanguage("en");
    return volumeInfo;
  }
}