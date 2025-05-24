package com.bookflux.controller;

import com.bookflux.dto.EndReadingSessionRequest;
import com.bookflux.dto.StartReadingSessionRequest;
import com.bookflux.integration.service.ReadingSessionService;
import com.bookflux.repository.collection.ReadingSession;
import com.bookflux.repository.collection.UserBookCollection;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class ReadingSessionController {

    private final ReadingSessionService sessionService;

    @PostMapping("/start-reading-session")
    public ResponseEntity<ReadingSession> startSession(@RequestBody StartReadingSessionRequest request) {
        UserBookCollection userBook = request.getUserBookCollection();
        ReadingSession session = sessionService.startSession(request, userBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(session);
    }

    @PostMapping("/finish-reading-session/")
    public ResponseEntity<ReadingSession> finishSession(@RequestBody @Valid EndReadingSessionRequest endReadingSessionRequest) {
        ReadingSession result = sessionService.finishSession(endReadingSessionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


}