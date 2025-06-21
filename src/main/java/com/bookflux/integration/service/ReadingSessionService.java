package com.bookflux.integration.service;

import com.bookflux.dto.google.EndReadingSessionRequest;
import com.bookflux.dto.google.StartReadingSessionRequest;
import com.bookflux.repository.collection.ReadingSession;

public interface ReadingSessionService {

  public ReadingSession startSession(StartReadingSessionRequest readingSessionRequest);

  public ReadingSession finishSession(EndReadingSessionRequest readingSessionRequest);
}
