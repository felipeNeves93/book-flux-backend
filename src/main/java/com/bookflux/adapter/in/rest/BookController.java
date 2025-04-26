package com.bookflux.adapter.in.rest;

import com.bookflux.aplication.mapper.BookMapper;
import com.bookflux.aplication.service.BookCollectionApiService;
import com.bookflux.dto.GoogleBooksResponseDto;
import com.bookflux.repository.collection.BookCollection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookCollectionApiService bookCollectionApiService;

    public BookController(BookCollectionApiService bookCollectionApiService) {
        this.bookCollectionApiService = bookCollectionApiService;
    }

    @GetMapping
    public ResponseEntity<BookCollection> searchBook(@RequestParam String query) {
        GoogleBooksResponseDto response = bookCollectionApiService.searchBook(query);
        BookCollection book = BookMapper.toDomain(response);
        return ResponseEntity.ok(book);
    }
}