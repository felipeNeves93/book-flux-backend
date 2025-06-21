package com.bookflux.service.book;

import com.bookflux.integration.service.BookCollectionApiService;
import com.bookflux.repository.BookRepository;
import com.bookflux.repository.collection.book.BookCollection;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookCollectionServiceImpl implements BookCollectionService {

  private final BookRepository bookRepository;
  private final BookCollectionApiService bookCollectionApiService;

  @Override
  public BookCollection save(BookCollection bookCollection) {
    return bookRepository.save(bookCollection);
  }

  @Override
  public List<BookCollection> findByTitle(String title) {
    return List.of();
  }

  @Override
  public List<BookCollection> findByTitleExternally(String title) {
    return List.of();
  }

  @Override
  public Optional<BookCollection> findById(String bookId) {
    return Optional.empty();
  }
}
