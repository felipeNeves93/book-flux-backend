package com.bookflux.repository.collection;

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
@NoArgsConstructor
@AllArgsConstructor
public class SummarizedUser {

  private String userId;
  private String email;
  private String username;
  private String profilePictureUrl;
}
