package com.bookflux.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bookflux.MongoRepositoryTestContext;
import com.bookflux.repository.collection.user.User;
import com.bookflux.repository.collection.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class UserRepositoryTest extends MongoRepositoryTestContext {

  @Autowired
  private UserRepository userRepository;

  @BeforeEach
  void tearDow() {
    userRepository.deleteAll();
  }

  @DisplayName("Should find user by email")
  @Test
  void shouldFindUserByEmail() {
    var user = User.builder()
        .email("felipe@email.com")
        .username("felipe")
        .password("felipe123")
        .build();

    userRepository.save(user);

    var searchedUser = userRepository.findByEmail(user.getEmail());

    assertTrue(searchedUser.isPresent());
  }

  @DisplayName("Should not find user when user does not exists by email")
  @Test
  void shouldNotFindUserWhenUserDoesNotExistsByEmail() {
    assertFalse(userRepository.findByEmail("aEmail.com").isPresent());
  }

  @DisplayName("Should find user by username")
  @Test
  void shouldFindUserByUsername() {
    var user = User.builder()
        .email("felipe@email.com")
        .username("felipe")
        .password("felipe123")
        .build();

    userRepository.save(user);

    var searchedUser = userRepository.findByUsername(user.getUsername());

    assertTrue(searchedUser.isPresent());
  }

  @DisplayName("Should not find user when user does not exists by username")
  @Test
  void shouldNotFindUserWhenUserDoesNotExistsByUsername() {
    assertFalse(userRepository.findByEmail("auser.com").isPresent());
  }
}
