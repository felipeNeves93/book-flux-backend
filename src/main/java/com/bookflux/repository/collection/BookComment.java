package com.bookflux.repository.collection;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookComment {

  private String id;
  private String bookId;
  private String content;
  private SummarizedUser user;
  private LocalDateTime dateAdded;
  private Integer likes;

}
