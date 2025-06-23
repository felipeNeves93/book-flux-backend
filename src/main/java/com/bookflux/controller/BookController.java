package com.bookflux.controller;

import com.bookflux.integration.mapper.BookMapper;
import com.bookflux.integration.service.BookCollectionApiService;
import com.bookflux.repository.collection.book.BookCollection;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {

  private final BookCollectionApiService bookCollectionApiService;

  public BookController(BookCollectionApiService bookCollectionApiService) {
    this.bookCollectionApiService = bookCollectionApiService;
  }

  @GetMapping
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<List<BookCollection>> searchBook(@RequestParam String query) {
    var response = bookCollectionApiService.searchBook(query);
    var book = BookMapper.fromGoogleApiResponse(response.get());
    return ResponseEntity.ok(book);
  }
}