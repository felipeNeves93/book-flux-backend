package com.bookflux.integration.service;

import com.bookflux.dto.google.GoogleBooksResponseDto;
import java.util.Optional;


public interface BookCollectionApiService {


  Optional<GoogleBooksResponseDto> searchBook(String query);
}
