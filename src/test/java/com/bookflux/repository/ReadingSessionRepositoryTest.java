package com.bookflux.repository;

import com.bookflux.repository.collection.ReadingSession;
import com.bookflux.repository.collection.ReadingSessionStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataMongoTest
class ReadingSessionRepositoryTest {

    @Autowired
    private ReadingSessionRepository repository;

    @Test
    @DisplayName("Should save and retrieve a ReadingSession by sessionId")
    void shouldSaveAndFindBySessionId() {
        String sessionId = UUID.randomUUID().toString();

        ReadingSession session = new ReadingSession();
        session.setSessionId(sessionId);
        session.setBookId("book-generic");
        session.setUserId("user-generic");
        session.setStartTime(LocalDateTime.now());
        session.setEndTime(LocalDateTime.now().plusHours(2));
        session.setStatus(ReadingSessionStatus.IN_PROGRESS);
        session.setNumberOfPagesRead(10);

        repository.save(session);

        Optional<ReadingSession> result = repository.findBySessionId(sessionId);

        assertTrue(result.isPresent());
        assertEquals(sessionId, result.get().getSessionId());
        assertEquals("book-generic", result.get().getBookId());
        assertEquals(ReadingSessionStatus.IN_PROGRESS, result.get().getStatus());
    }
}