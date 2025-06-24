package com.bookflux;

import com.bookflux.dto.google.GoogleBooksResponseDto;
import com.bookflux.dto.google.ImagelinksDto;
import java.util.Arrays;
import java.util.Collections;

public final class DummyObjectFactory {

  private DummyObjectFactory() {

  }

  public static GoogleBooksResponseDto createDummyGoogleBooksResponseDto() {
    var imageLinks = new ImagelinksDto();
    imageLinks.setSmallThumbnail("http://example.com/smallThumbnail.jpg");
    imageLinks.setThumbnail("http://example.com/thumbnail.jpg");

    var volumeInfo = new GoogleBooksResponseDto.VolumeInfo();
    volumeInfo.setTitle("The Great Novel");
    volumeInfo.setAuthors(Arrays.asList("John Doe", "Jane Smith"));
    volumeInfo.setPublisher("Example Publishing");
    volumeInfo.setPublishedDate("2023-06-15");
    volumeInfo.setDescription("A thrilling tale of adventure and discovery.");
    volumeInfo.setPageCount(350);
    volumeInfo.setCategories(Arrays.asList("Fiction", "Adventure"));
    volumeInfo.setMaturityRating("NOT_MATURE");
    volumeInfo.setAverageRating(4.2);
    volumeInfo.setImageLinks(imageLinks);
    volumeInfo.setLanguage("en");

    GoogleBooksResponseDto.Item item = new GoogleBooksResponseDto.Item();
    item.setVolumeInfo(volumeInfo);

    GoogleBooksResponseDto responseDto = new GoogleBooksResponseDto();
    responseDto.setItems(Collections.singletonList(item));

    return responseDto;
  }

}
