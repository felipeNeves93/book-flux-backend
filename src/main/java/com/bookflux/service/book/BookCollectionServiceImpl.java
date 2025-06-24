package com.bookflux.service.book;

import com.bookflux.integration.mapper.BookMapper;
import com.bookflux.integration.service.BookCollectionApiService;
import com.bookflux.repository.BookRepository;
import com.bookflux.repository.collection.book.BookCollection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookCollectionServiceImpl implements BookCollectionService {

  private final BookRepository bookRepository;
  private final BookCollectionApiService bookCollectionApiService;

  @Override
  public BookCollection save(BookCollection bookCollection) {
    return bookRepository.save(bookCollection);
  }

  @Override
  public List<BookCollection> findByTitle(String title) {
    return bookRepository.findByTitleContainingIgnoreCase(title);
  }

  @Override
  public List<BookCollection> findByTitleExternally(String title) {
    log.debug("Searching externally for title {}", title);
    var googleBooksApiResponse = bookCollectionApiService.searchBook(title);

    if (googleBooksApiResponse.isEmpty()) {
      log.debug("No books found for title {}", title);
      return Collections.emptyList();
    }

    log.debug("Found {} books for title {}", googleBooksApiResponse.get().getItems().size(), title);
    var googleBooks = BookMapper.fromGoogleApiResponse(googleBooksApiResponse.get());

    bookRepository.saveAll(googleBooks);
    log.debug("Saved {} books for title {}", googleBooks.size(), title);

    return googleBooks;
  }

  @Override
  public Optional<BookCollection> findById(String bookId) {
    return bookRepository.findById(bookId);
  }
}
