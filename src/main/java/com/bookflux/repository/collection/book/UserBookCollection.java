package com.bookflux.repository.collection.book;

import com.bookflux.repository.collection.ReadingSession;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userBookCollection")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserBookCollection {

  @Id
  private String bookId;

  @Indexed
  private String title;

  private List<String> authors;
  private String description;
  private List<BookComment> comments;
  private List<UserReview> userReviews;
  private boolean isRead;
  private boolean isFavorite;
  private boolean wantToRead;
  private Integer stars;
  private LocalDateTime dateAdded;
  private String userId;
  private List<ReadingSession> readingSessions;

}
