package com.bookflux.integration.service;

import com.bookflux.dto.google.GoogleBooksResponseDto;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class BookCollectionApiServiceImpl implements BookCollectionApiService {

  @Value("${google.books.api.url}")
  private String apiUrl;

  private final RestTemplate rest;

  public BookCollectionApiServiceImpl(RestTemplate restTemplate) {
    this.rest = restTemplate;
  }

  @Override
  public Optional<GoogleBooksResponseDto> searchBook(String query) {
    try {
      var url = apiUrl + query;
      return Optional.ofNullable(rest.getForObject(url, GoogleBooksResponseDto.class));
    } catch (Exception e) {
      log.error("Error when calling Google Books API!", e);
      return Optional.empty();
    }
  }
}
