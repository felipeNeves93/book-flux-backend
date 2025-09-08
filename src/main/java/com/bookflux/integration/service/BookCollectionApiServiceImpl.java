package com.bookflux.integration.service;

import com.bookflux.config.exception.BookNotFoundException;
import com.bookflux.dto.GoogleBooksResponseDto;
import com.bookflux.repository.ReadingSessionRepository;
import com.bookflux.repository.UserBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookCollectionApiServiceImpl implements BookCollectionApiService {

    @Value("${google.books.api.url}")
    private String apiUrl;

    private final RestTemplate rest;
    private final UserBookRepository userBookRepository;
    private  final ReadingSessionRepository readingSessionRepository;


    public BookCollectionApiServiceImpl(RestTemplate restTemplate, UserBookRepository userBookRepository, ReadingSessionRepository readingSessionRepository) {
        this.rest = restTemplate;

        this.userBookRepository = userBookRepository;
        this.readingSessionRepository = readingSessionRepository;
    }



    @Override
    public GoogleBooksResponseDto searchBook(String query) {
        String url = apiUrl + query;
        return rest.getForObject(url, GoogleBooksResponseDto.class);
    }


    @Override
    public void deleteBookAndSessions(String id) {
        userBookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));
        readingSessionRepository.deleteByBookId(id);
        userBookRepository.deleteById(id);
    }
}
