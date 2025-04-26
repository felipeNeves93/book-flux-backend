package com.bookflux.integration.service;

import com.bookflux.dto.GoogleBooksResponseDto;

public interface BookCollectionApiService {

    public GoogleBooksResponseDto searchBook(String query);
}
