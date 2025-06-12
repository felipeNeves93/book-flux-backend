package com.bookflux.repository;

import com.bookflux.repository.collection.ReadingSession;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ReadingSessionRepository extends MongoRepository<ReadingSession, String> {

    Optional<ReadingSession> findBySessionId(String sessionId);

    List<ReadingSession> findByUserId(String userId);

    List<ReadingSession> findByBookId(String bookId);

    List<ReadingSession> findByUserIdAndBookId(String userId, String bookId);
}
