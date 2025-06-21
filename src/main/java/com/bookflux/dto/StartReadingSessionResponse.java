package com.bookflux.dto;

import com.bookflux.repository.collection.ReadingSessionStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class StartReadingSessionResponse {

    private String sessionId;
    private String bookId;
    private String userId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ReadingSessionStatus status;
    private Integer numberOfPagesRead;
}
