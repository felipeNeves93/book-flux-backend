package com.bookflux.controller;

import com.bookflux.dto.EndReadingSessionRequest;
import com.bookflux.dto.StartReadingSessionRequest;
import com.bookflux.integration.service.SessionService;
import com.bookflux.repository.collection.ReadingSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class ReadingSessionController {

    private final SessionService sessionService;

    @PostMapping("/start-reading-session")
    public ResponseEntity<ReadingSession> startSession(@RequestBody StartReadingSessionRequest request) {
        ReadingSession session = sessionService.startSession(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(session);
    }

    @PatchMapping("/finish-reading-session/{sessionId}/{numberOfPages}")
    public ResponseEntity<ReadingSession> finishSession(

            @PathVariable String sessionId,
            @PathVariable Integer numberOfPages) {

        EndReadingSessionRequest request = new EndReadingSessionRequest();
        request.setSessionId(sessionId);
        request.setNumberOfPagesRead(numberOfPages);
        ReadingSession result = sessionService.finishSession(request);
        return ResponseEntity.ok(result);
    }


}