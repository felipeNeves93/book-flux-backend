package com.bookflux.repository.collection.user;

import com.bookflux.config.mongo.SharedEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
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

  @NotBlank
  @Indexed(unique = true)
  private String email;

  @NotBlank
  private String password;

  @NotBlank
  @Indexed(unique = true)
  private String username;

  private String profilePicture;

}
