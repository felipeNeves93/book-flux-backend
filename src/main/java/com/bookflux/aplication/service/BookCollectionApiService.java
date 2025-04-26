package com.bookflux.aplication.service;

import com.bookflux.dto.GoogleBooksResponseDto;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

public interface BookCollectionApiService {

    public GoogleBooksResponseDto searchBook(String query);
}
