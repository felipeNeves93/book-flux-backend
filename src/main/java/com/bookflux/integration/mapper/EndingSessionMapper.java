package com.bookflux.integration.mapper;

import com.bookflux.dto.google.EndReadingSessionResponse;
import com.bookflux.repository.collection.ReadingSession;

public final class EndingSessionMapper {

    private EndingSessionMapper() {

    }

    public static EndReadingSessionResponse mapToResponse(ReadingSession session) {
        return EndReadingSessionResponse.builder()
                .sessionId(session.getSessionId())
                .bookId(session.getBookId())
                .userId(session.getUserId())
                .startTime(session.getStartTime())
                .endTime(session.getEndTime())
                .status(session.getStatus())
                .numberOfPagesRead(session.getNumberOfPagesRead())
                .build();
    }
}