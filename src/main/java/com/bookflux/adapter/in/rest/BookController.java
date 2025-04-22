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
    private final BookMapper mapper;

    public BookController(BookCollectionApiService bookCollectionApiService, BookMapper mapper) {
        this.bookCollectionApiService = bookCollectionApiService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<BookCollection> searchBook(@RequestParam String query){
        GoogleBooksResponseDto response = bookCollectionApiService.searchBook(query);
        return ResponseEntity.ok(mapper.toDomain(response));

    }
}
