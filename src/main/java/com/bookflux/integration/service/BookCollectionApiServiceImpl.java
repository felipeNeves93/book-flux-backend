package com.bookflux.integration.service;

import com.bookflux.dto.google.GoogleBooksResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookCollectionApiServiceImpl implements BookCollectionApiService{

    @Value("${google.books.api.url}")
    private String apiUrl;

    private final RestTemplate rest;

    public BookCollectionApiServiceImpl(RestTemplate restTemplate) {
        this.rest = restTemplate;
    }

    @Override
    public GoogleBooksResponseDto searchBook(String query) {
        String url = apiUrl + query;
        return rest.getForObject(url, GoogleBooksResponseDto.class);
    }
}
