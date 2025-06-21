package com.bookflux.dto.google;

import com.bookflux.repository.collection.ReadingSessionStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
public class EndReadingSessionRequest {

    @NotBlank
    private String sessionId;
    @NotNull
    private String userId;
    @Size(min = 1)
    private String bookId;

    @NotNull(message = "numberOfPagesRead is not optional")
    private Integer numberOfPagesRead;
    private ReadingSessionStatus status;
    private LocalDateTime endTime;
    private LocalDateTime startTime;

}

