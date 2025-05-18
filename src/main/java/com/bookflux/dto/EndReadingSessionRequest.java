package com.bookflux.dto;

import com.bookflux.repository.collection.ReadingSessionStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class EndReadingSessionRequest {

    @NotNull
    private String sessionId;
    @NotNull
    private String userId;
    @Min(value = 1)
    private Integer numberOfPagesRead;
    private ReadingSessionStatus status;
    private LocalDateTime endTime;

}

