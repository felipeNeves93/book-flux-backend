package com.bookflux.integration.mapper;

import com.bookflux.dto.google.StartReadingSessionResponse;
import com.bookflux.repository.collection.ReadingSession;

public final class ReadingSessionMapper {

    private ReadingSessionMapper() {
    }

    public static StartReadingSessionResponse maptoResponse(ReadingSession readingSession){
        return  StartReadingSessionResponse.builder().
                sessionId(readingSession.getSessionId()).
                bookId(readingSession.getBookId()).
                userId(readingSession.getUserId()).
                startTime(readingSession.getStartTime()).
                endTime(readingSession.getEndTime()).
                status(readingSession.getStatus()).
                numberOfPagesRead(readingSession.getNumberOfPagesRead()).
                build();

    }
}
