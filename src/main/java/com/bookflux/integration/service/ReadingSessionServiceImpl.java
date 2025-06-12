package com.bookflux.integration.service;

import com.bookflux.config.exception.InvalidReadingSessionException;
import com.bookflux.dto.EndReadingSessionRequest;
import com.bookflux.dto.StartReadingSessionRequest;
import com.bookflux.repository.ReadingSessionRepository;
import com.bookflux.repository.UserBookRepository;
import com.bookflux.repository.collection.ReadingSession;
import com.bookflux.repository.collection.ReadingSessionStatus;
import com.bookflux.repository.collection.book.UserBookCollection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReadingSessionServiceImpl implements ReadingSessionService {

  private final ReadingSessionRepository readingSessionRepository;
  private final UserBookRepository userBookRepository;

  @Override
  @Transactional
  public ReadingSession startSession(StartReadingSessionRequest readingSessionRequest) {

    String userBookId = readingSessionRequest.getUserBookCollection().getBookId();
    var userBook = userBookRepository.findByBookId(userBookId)
        .orElseThrow(() -> new InvalidReadingSessionException("UserBookCollection not found"));

    if (userBook.isRead()) {
      throw new InvalidReadingSessionException("The book has already been read");
    }

    ReadingSession session = new ReadingSession();
    session.setUserId(readingSessionRequest.getUserId());
    session.setBookId(readingSessionRequest.getBookId());
    session.setStartTime(LocalDateTime.now());
    session.setEndTime(readingSessionRequest.getEndTime());
    session.setStatus(ReadingSessionStatus.IN_PROGRESS);
    session.setSessionId(UUID.randomUUID().toString());
    session.setUserBookCollection(userBook);

    ReadingSession savedSession = readingSessionRepository.save(session);

    if (userBook.getReadingSessions() == null) {
      userBook.setReadingSessions(new ArrayList<>());
    }
    userBook.getReadingSessions().add(savedSession);

    userBookRepository.save(userBook);

    return savedSession;
  }

  @Override
  @Transactional
  public ReadingSession finishSession(EndReadingSessionRequest endReadingSessionRequest) {

    ReadingSession endSession = readingSessionRepository.findBySessionId(
            endReadingSessionRequest.getSessionId()).
        orElseThrow(() -> new InvalidReadingSessionException("Session not found"));

    if (endSession.getStatus() == ReadingSessionStatus.COMPLETE) {
      throw new InvalidReadingSessionException("Session already completed");
    } else if (endSession.getStatus() == ReadingSessionStatus.CANCELED) {
      throw new InvalidReadingSessionException("Session cannot be canceled");
    }

    endSession.setEndTime(LocalDateTime.now());
    endSession.setNumberOfPagesRead(endReadingSessionRequest.getNumberOfPagesRead());
    endSession.setStatus(ReadingSessionStatus.COMPLETE);

    ReadingSession savedSession = readingSessionRepository.save(endSession);

    UserBookCollection userBook = userBookRepository.findByUserIdAndBookId(
        savedSession.getUserId(),
        savedSession.getBookId()
    ).orElseThrow(() -> new InvalidReadingSessionException("UserBookCollection not found"));

    boolean exists = userBook.getReadingSessions().stream()
        .anyMatch(session -> session.getSessionId().equals(savedSession.getSessionId()));

    if (!exists) {
      throw new InvalidReadingSessionException("Reading session not found in user book collection");
    }

    List<ReadingSession> sessions = userBook.getReadingSessions();

    for (int i = 0; i < sessions.size(); i++) {
      if (sessions.get(i).getSessionId().equals(savedSession.getSessionId())) {
        sessions.set(i, savedSession);
        break;
      }
    }

    userBookRepository.save(userBook);

    return savedSession;


  }


}

