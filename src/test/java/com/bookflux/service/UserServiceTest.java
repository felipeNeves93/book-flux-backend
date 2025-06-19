package com.bookflux.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.bookflux.exception.UserAlreadyExistsException;
import com.bookflux.repository.collection.user.User;
import com.bookflux.repository.collection.user.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserServiceTest {

  private UserRepository userRepository;
  private UserService userService;

  @BeforeEach
  void setup() {
    this.userRepository = mock(UserRepository.class);
    this.userService = new UserServiceImpl(userRepository);
  }

  @DisplayName("When register already existing user the should throw exception")
  @Test
  void whenRegisterAlreadyExistingUserThenShouldThrowException() {
    var user = User.builder()
        .email("email")
        .build();

    when(userRepository.findByEmail(anyString()))
        .thenReturn(Optional.of(User.builder().build()));

    assertThrows(UserAlreadyExistsException.class, () -> userService.registerUser(user));
  }

  @DisplayName("When register user with existing username then should throw exception")
  @Test
  void whenRegisterUserWithExistingUsernameThenShouldThrowException() {
    var user = User.builder()
        .email("email")
        .username("username")
        .build();

    when(userRepository.findByEmail(anyString()))
        .thenReturn(Optional.empty());

    when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

    assertThrows(UserAlreadyExistsException.class, () -> userService.registerUser(user));
  }

  @DisplayName("When saving a valid user then should return saved user")
  @Test
  void whenSavingAValidUserThenShouldReturnSavedUser() {
    var user = User.builder()
        .email("email")
        .username("username")
        .build();

    when(userRepository.findByEmail(anyString()))
        .thenReturn(Optional.empty());

    when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

    when(userRepository.save(any())).thenReturn(user);

    var savedUser = userService.registerUser(user);

    assertNotNull(savedUser);
  }

}
