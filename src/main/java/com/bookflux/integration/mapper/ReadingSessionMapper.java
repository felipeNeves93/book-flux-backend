package com.bookflux.integration.mapper;

import com.bookflux.dto.StartReadingSessionResponse;
import com.bookflux.repository.collection.ReadingSession;

public class ReadingSessionMapper {
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
