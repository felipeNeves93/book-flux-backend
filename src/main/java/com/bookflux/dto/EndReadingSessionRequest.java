package com.bookflux.dto;

import com.bookflux.repository.collection.ReadingSessionStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EndReadingSessionRequest {
    private String sessionId;
    private String userId;
    private Integer numberOfPagesRead;
    private ReadingSessionStatus status;
    private LocalDateTime endTime;

}
