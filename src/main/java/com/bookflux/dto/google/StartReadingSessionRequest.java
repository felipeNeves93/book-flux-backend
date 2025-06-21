package com.bookflux.dto.google;

import com.bookflux.repository.collection.book.UserBookCollection;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
