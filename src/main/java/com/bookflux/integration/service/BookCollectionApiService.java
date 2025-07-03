package com.bookflux.integration.service;

import com.bookflux.dto.GoogleBooksResponseDto;


public interface BookCollectionApiService {

    GoogleBooksResponseDto searchBook(String query);

    void deleteBookAndSessions(String id);
}
