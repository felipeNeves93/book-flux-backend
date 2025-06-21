package com.bookflux.integration.dto;

import com.bookflux.dto.StartReadingSessionRequest;
import com.bookflux.repository.collection.ReadingSession;
import com.bookflux.repository.collection.ReadingSessionStatus;
import com.bookflux.repository.collection.UserBookCollection;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

public class TestDataFactory {

    public static StartReadingSessionRequest buildStartReadingSessionRequest() {
        return StartReadingSessionRequest.builder()
                .bookId("book-182")
                .userId("user-132")
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusHours(1))
                .userBookCollection(buildUserBookCollection())
                .build();
    }

    public static UserBookCollection buildUserBookCollection() {
        UserBookCollection collection = new UserBookCollection();
        collection.setBookId("book-182");
        collection.setUserId("user-132");
        collection.setTitle("The Hobbit");
        collection.setAuthors(Collections.singletonList("J.R.R. Tolkien"));
        collection.setDescription("A children's fantasy novel");
        collection.setRead(false);
        collection.setFavorite(true);
        collection.setWantToRead(false);
        collection.setStars(5);
        collection.setDateAdded(LocalDateTime.now().minusDays(2));
        collection.setReadingSessions(Collections.emptyList());
        return collection;
    }

    public static ReadingSession buildReadingSession() {
        ReadingSession session = new ReadingSession();
        session.setSessionId(UUID.randomUUID().toString());
        session.setBookId("book-182");
        session.setUserId("user-132");
        session.setStartTime(LocalDateTime.now());
        session.setEndTime(LocalDateTime.now().plusHours(1));
        session.setStatus(ReadingSessionStatus.IN_PROGRESS);
        session.setNumberOfPagesRead(0);
        session.setUserBookCollection(buildUserBookCollection());
        return session;
    }
}