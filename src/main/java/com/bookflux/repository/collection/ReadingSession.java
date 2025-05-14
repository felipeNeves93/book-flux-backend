package com.bookflux.repository.collection;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "readingSessions")
public class ReadingSession {
    private String sessionId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String bookId;
    private ReadingSessionStatus status;
    private String userId;
    private LocalDateTime creationDate = LocalDateTime.now();
    private Integer numberOfPagesRead;

}
