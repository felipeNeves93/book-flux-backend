package com.bookflux.dto;

import com.bookflux.repository.collection.ReadingSessionStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
public class EndReadingSessionRequest {

    @NotNull
    private String sessionId;
    @NotNull
    private String userId;
    @Size(min = 1)
    private String bookId;
    private Integer numberOfPagesRead;
    private ReadingSessionStatus status;
    private LocalDateTime endTime;
    private LocalDateTime startTime;

}

