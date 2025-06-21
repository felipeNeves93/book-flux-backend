package com.bookflux.integration.mapper;

import com.bookflux.dto.StartReadingSessionResponse;
import com.bookflux.repository.collection.ReadingSession;
import com.bookflux.repository.collection.ReadingSessionStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ReadingSessionMapperTest {

    @Test
    @DisplayName("Should map ReadingSession to StartReadingSessionResponse correctly")
    void shouldMapReadingSessionToResponse() {
        ReadingSession session = new ReadingSession();
        session.setSessionId(UUID.randomUUID().toString());
        session.setBookId("book-generic");
        session.setUserId("user-generic");
        session.setStartTime(LocalDateTime.now());
        session.setEndTime(LocalDateTime.now().plusHours(1));
        session.setStatus(ReadingSessionStatus.IN_PROGRESS);
        session.setNumberOfPagesRead(10);

        StartReadingSessionResponse response = ReadingSessionMapper.maptoResponse(session);

        assertNotNull(response);
        assertEquals(session.getSessionId(), response.getSessionId());
        assertEquals(session.getBookId(), response.getBookId());
        assertEquals(session.getUserId(), response.getUserId());
        assertEquals(session.getStartTime(), response.getStartTime());
        assertEquals(session.getEndTime(), response.getEndTime());
        assertEquals(session.getStatus(), response.getStatus());
        assertEquals(session.getNumberOfPagesRead(), response.getNumberOfPagesRead());
    }
}
