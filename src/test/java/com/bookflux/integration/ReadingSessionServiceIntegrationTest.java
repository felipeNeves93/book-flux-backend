package com.bookflux.integration;

import com.bookflux.MongoRepositoryTestContext;
import com.bookflux.dto.StartReadingSessionRequest;
import com.bookflux.integration.service.ReadingSessionServiceImpl;
import com.bookflux.repository.ReadingSessionRepository;
import com.bookflux.repository.UserBookRepository;
import com.bookflux.repository.collection.ReadingSession;
import com.bookflux.repository.collection.ReadingSessionStatus;
import com.bookflux.repository.collection.UserBookCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


@Import(ReadingSessionServiceImpl.class)
public class ReadingSessionServiceIntegrationTest extends MongoRepositoryTestContext {

        @Autowired
        private ReadingSessionServiceImpl readingSessionService;

        @Autowired
        private ReadingSessionRepository readingSessionRepository;

        @Autowired
        private UserBookRepository userBookRepository;

        @BeforeEach
        void setUp() {
            UserBookCollection userBookCollection = new UserBookCollection();
            userBookCollection.setBookId("1");
            userBookCollection.setTitle("The Hobbit");
            userBookCollection.setAuthors(Collections.singletonList("J.R.R. Tolkien"));
            userBookCollection.setDescription("Lorem");
            userBookCollection.setWantToRead(false);
            userBookCollection.setStars(5);
            userBookCollection.setDateAdded(LocalDateTime.now());
            userBookCollection.setUserId("user-1");

            userBookRepository.save(userBookCollection);
        }

        @Test
        @DisplayName("Should start a reading session and persist it")
        void shouldStartReadingSession() {
            StartReadingSessionRequest request = new StartReadingSessionRequest();
            request.setBookId("1");
            request.setUserId("user-1");
            request.setEndTime(LocalDateTime.now().plusHours(2));
            request.setUserBookCollection(userBookRepository.findByBookId("1").orElseThrow());

            ReadingSession savedSession = readingSessionService.startSession(request);

            assertThat(savedSession).isNotNull();
            assertThat(savedSession.getSessionId()).isNotBlank();
            assertThat(savedSession.getBookId()).isEqualTo("1");
            assertThat(savedSession.getUserId()).isEqualTo("user-1");
            assertThat(savedSession.getStatus()).isEqualTo(ReadingSessionStatus.IN_PROGRESS);

            UserBookCollection persistedBook = userBookRepository.findByBookId("1").orElseThrow();
            assertThat(persistedBook.getReadingSessions())
                    .extracting(ReadingSession::getSessionId)
                    .contains(savedSession.getSessionId());
        }

    @Test
    @DisplayName("Should throw exception when UserBookCollection is not found")
    void shouldThrowExceptionWhenUserBookCollectionNotFound() {
        StartReadingSessionRequest request = new StartReadingSessionRequest();
        request.setBookId("non-existent-book");
        request.setUserId("user-2");
        request.setEndTime(LocalDateTime.now().plusHours(2));

        assertThatThrownBy(() -> {
            request.setUserBookCollection(
                    userBookRepository.findByBookId("non-existent-book").orElseThrow()
            );
            readingSessionService.startSession(request);
        });

    }



}

