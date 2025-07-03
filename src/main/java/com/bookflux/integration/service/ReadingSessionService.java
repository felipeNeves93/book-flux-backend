package com.bookflux.integration.service;

import com.bookflux.dto.EndReadingSessionRequest;
import com.bookflux.dto.StartReadingSessionRequest;
import com.bookflux.repository.collection.ReadingSession;
import lombok.Locked;

public interface ReadingSessionService  {
    public ReadingSession startSession(StartReadingSessionRequest readingSessionRequest);
    public ReadingSession finishSession(EndReadingSessionRequest readingSessionRequest);
    public void deleteSession(String id);
}
