    package com.bookflux.dto;

    import com.bookflux.repository.collection.UserBookCollection;
    import jakarta.validation.Valid;
    import jakarta.validation.constraints.Future;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.NotNull;
    import jakarta.validation.constraints.PastOrPresent;
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
        @NotBlank
        private String bookId;
        @NotBlank
        private String userId;
        @PastOrPresent
        private LocalDateTime startTime;
        @Future
        private LocalDateTime endTime;

        @NotNull
        @Valid
        private UserBookCollection userBookCollection;
    }
