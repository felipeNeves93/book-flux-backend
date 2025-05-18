package com.bookflux.integration.service;

import com.bookflux.config.exception.InvalidReadingSessionException;
import com.bookflux.dto.EndReadingSessionRequest;

import com.bookflux.dto.StartReadingSessionRequest;
import com.bookflux.repository.ReadingSessionRepository;
import com.bookflux.repository.UserBookRepository;
import com.bookflux.repository.collection.ReadingSession;
import com.bookflux.repository.collection.ReadingSessionStatus;
import com.bookflux.repository.collection.UserBookCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class ReadingSessionServiceImpl  implements ReadingSessionService  {

    @Autowired
    private ReadingSessionRepository readingSessionRepository;

    @Autowired
    private UserBookRepository userBookRepository;

    @Override
    public ReadingSession  startSession(StartReadingSessionRequest readingSessionRequest, UserBookCollection userBookCollection) {

        if (userBookCollection.isRead()) {
            throw new InvalidReadingSessionException("The book has already been read");
        }
        ReadingSession session = new ReadingSession();
        session.setUserId(readingSessionRequest.getUserId());
        session.setBookId(readingSessionRequest.getBookId());
        session.setStartTime(LocalDateTime.now());
        session.setEndTime(readingSessionRequest.getEndTime());
        session.setStatus(ReadingSessionStatus.IN_PROGRESS);
        session.setSessionId(UUID.randomUUID().toString());
        ReadingSession savedSession = readingSessionRepository.save(session);
        if (userBookCollection.getReadingSessions().isEmpty()){
            userBookCollection.setReadingSessions(new ArrayList<>());
        }
        userBookCollection.getReadingSessions().add(savedSession);
        userBookRepository.save(userBookCollection);
        return savedSession;
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

