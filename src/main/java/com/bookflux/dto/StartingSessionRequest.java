package com.bookflux.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class StartingSessionRequest {
    String sessionId;
    private Integer numberOfPagesRead;
}
