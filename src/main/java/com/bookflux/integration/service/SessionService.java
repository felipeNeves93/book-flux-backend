package com.bookflux.integration.service;

import com.bookflux.dto.EndReadingSessionRequest;
import com.bookflux.dto.StartReadingSessionRequest;
import com.bookflux.repository.collection.ReadingSession;

public interface SessionService {
    public ReadingSession startSession(StartReadingSessionRequest readingSessionRequest);
    public ReadingSession finishSession(EndReadingSessionRequest readingSessionRequest);
}
