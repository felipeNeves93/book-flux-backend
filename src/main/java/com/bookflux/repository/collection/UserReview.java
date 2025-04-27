package com.bookflux.repository.collection;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserReview {

  private String id;
  private SummarizedUser user;
  private String bookId;
  private String title;
  private String content;
  private Integer stars;
  private LocalDate createdDate;

}
