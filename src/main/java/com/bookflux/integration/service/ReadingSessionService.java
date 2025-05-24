package com.bookflux.integration.service;

import com.bookflux.dto.EndReadingSessionRequest;
import com.bookflux.dto.StartReadingSessionRequest;
import com.bookflux.repository.collection.ReadingSession;
import com.bookflux.repository.collection.UserBookCollection;

    public interface ReadingSessionService  {
    public ReadingSession startSession(StartReadingSessionRequest readingSessionRequest, UserBookCollection userBookCollection);
    public ReadingSession finishSession(EndReadingSessionRequest readingSessionRequest);
}
