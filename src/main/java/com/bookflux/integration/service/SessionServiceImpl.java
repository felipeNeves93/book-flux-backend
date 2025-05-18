package com.bookflux.integration.service;

import com.bookflux.dto.EndReadingSessionRequest;

import com.bookflux.dto.StartReadingSessionRequest;
import com.bookflux.repository.collection.ReadingSession;
import com.bookflux.repository.collection.ReadingSessionStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SessionServiceImpl implements SessionService {

    @Override
    public ReadingSession  startSession(StartReadingSessionRequest readingSessionRequest) {

        if (readingSessionRequest.read) {
            throw new IllegalStateException("The book has already been read");
        }
        ReadingSession session = new ReadingSession();
        session.setUserId(readingSessionRequest.getUserId());
        session.setBookId(readingSessionRequest.getBookId());
        session.setStartTime(LocalDateTime.now());
        session.setEndTime(readingSessionRequest.getEndTime());
        session.setStatus(ReadingSessionStatus.IN_PROGRESS);
        session.setSessionId(UUID.randomUUID().toString());
        return session;
    }

    @Override
    public ReadingSession finishSession(EndReadingSessionRequest readingSessionRequest) {
        ReadingSession finishSession = new ReadingSession();
        finishSession.setEndTime(LocalDateTime.now());
        finishSession.setNumberOfPagesRead(readingSessionRequest.getNumberOfPagesRead());
        finishSession.setStatus(ReadingSessionStatus.COMPLETE);
        finishSession.setUserId(readingSessionRequest.getUserId());
        finishSession.setBookId(readingSessionRequest.getBookId());
        finishSession.setStartTime(readingSessionRequest.getStartTime());
        finishSession.setSessionId(readingSessionRequest.getSessionId());
        return finishSession;
    }


    }

