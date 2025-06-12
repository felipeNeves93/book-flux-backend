package com.bookflux.dto;

import com.bookflux.repository.collection.ReadingSessionStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EndReadingSessionResponse {
    private String sessionId;
    private String bookId;
    private String userId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ReadingSessionStatus status;
    private Integer numberOfPagesRead;
}