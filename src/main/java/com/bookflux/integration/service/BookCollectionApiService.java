package com.bookflux.integration.service;

import com.bookflux.dto.google.GoogleBooksResponseDto;


public interface BookCollectionApiService {

  /**
   * Calls the Google Books API to search for a book with the given query.
   *
   * @param query the search query
   * @return a GoogleBooksResponseDto containing the search result
   */
  GoogleBooksResponseDto searchBook(String query);
}
