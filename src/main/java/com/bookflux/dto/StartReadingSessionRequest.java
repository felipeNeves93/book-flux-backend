    package com.bookflux.dto;

    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.time.LocalDateTime;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class StartReadingSessionRequest {
        private String bookId;
        private String userId;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        public boolean read;
    }
