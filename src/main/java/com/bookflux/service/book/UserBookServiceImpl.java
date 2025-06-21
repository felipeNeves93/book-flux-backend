package com.bookflux.service.book;

import com.bookflux.integration.service.BookCollectionApiService;
import com.bookflux.integration.service.ReadingSessionService;
import com.bookflux.repository.UserBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserBookServiceImpl {

  private final UserBookRepository userBookRepository;
  private final ReadingSessionService readingSessionService;
  private final BookCollectionApiService bookCollectionApiService;

}
