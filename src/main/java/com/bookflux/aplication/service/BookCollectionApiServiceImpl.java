package com.bookflux.aplication.service;

import com.bookflux.dto.GoogleBooksResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Component
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
