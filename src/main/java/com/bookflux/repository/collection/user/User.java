package com.bookflux.repository.collection.user;

import com.bookflux.config.mongo.SharedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SharedEntity
public class User {

  @Id
  private String id;
  private String email;
  private String password;
  private String username;
  private String profilePicture;

}
