package com.bookflux.repository.collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "readingSessions")
public class ReadingSession {
    @Indexed
    private String bookId;
    private String sessionId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ReadingSessionStatus status;
    private String userId;
    private LocalDateTime creationDate = LocalDateTime.now();
    private Integer numberOfPagesRead;

    @DBRef
    @JsonIgnore
    private UserBookCollection userBookCollection;

}
