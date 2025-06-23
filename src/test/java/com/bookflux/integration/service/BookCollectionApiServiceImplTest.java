package com.bookflux.integration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bookflux.dto.google.GoogleBooksResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

class BookCollectionApiServiceImplTest {

  @Mock
  private RestTemplate restTemplate;

  @InjectMocks
  private BookCollectionApiServiceImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    service = new BookCollectionApiServiceImpl(restTemplate);
    ReflectionTestUtils.setField(service, "apiUrl",
        "https://www.googleapis.com/books/v1/volumes?q=");
  }

  @DisplayName("When searching book should call api")
  @Test
  void whenSearchingBookShouldCallApi() {
    var query = "harry";
    var expectedUrl = "https://www.googleapis.com/books/v1/volumes?q=harry";

    var mockResponse = new GoogleBooksResponseDto();
    when(restTemplate.getForObject(expectedUrl, GoogleBooksResponseDto.class)).thenReturn(
        mockResponse);

    var result = service.searchBook(query);

    assertTrue(result.isPresent());
    assertEquals(mockResponse, result.get());
    verify(restTemplate, times(1)).getForObject(expectedUrl, GoogleBooksResponseDto.class);
  }
}