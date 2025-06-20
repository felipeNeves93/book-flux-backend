package com.bookflux.controller;

import com.bookflux.dto.EndReadingSessionRequest;
import com.bookflux.dto.EndReadingSessionResponse;
import com.bookflux.dto.StartReadingSessionRequest;
import com.bookflux.dto.StartReadingSessionResponse;
import com.bookflux.integration.mapper.EndingSessionMapper;
import com.bookflux.integration.mapper.ReadingSessionMapper;
import com.bookflux.integration.service.ReadingSessionService;
import com.bookflux.repository.collection.ReadingSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/reading-session")
@RequiredArgsConstructor
public class ReadingSessionController {

    private final ReadingSessionService sessionService;

    @PostMapping("/start")
    public ResponseEntity<StartReadingSessionResponse> startSession(@RequestBody @Valid StartReadingSessionRequest request) {

        ReadingSession session = sessionService.startSession(request);
        StartReadingSessionResponse response = ReadingSessionMapper.maptoResponse(session);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/finish")
    public ResponseEntity<EndReadingSessionResponse> finishSession(@RequestBody @Valid EndReadingSessionRequest endReadingSessionRequest) {

        sessionService.finishSession(endReadingSessionRequest);
        return ResponseEntity.noContent().build();
    }


}