package com.bookflux.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bookflux.WebMvcTestContext;
import com.bookflux.controller.requests.LoginRequest;
import com.bookflux.controller.requests.RegisterUserRequest;
import com.bookflux.repository.collection.user.User;
import com.bookflux.repository.collection.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

class AuthControllerTest extends WebMvcTestContext {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @BeforeEach
  void setup() {
    userRepository.deleteAll();
  }

  @DisplayName("Should register user successfully")
  @Test
  void shouldRegisterUserSuccessfully() throws Exception {
    var registerUserRequest = new RegisterUserRequest("felipe@email.com", "felipe", "felipe123",
        null);

    mockMvc.perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(registerUserRequest))).andExpect(status().isOk())
        .andExpect(content().string("User created successfully!"));
  }

  @Test
  @DisplayName("Should not allow duplicate email registration")
  void shouldNotRegisterUserWithDuplicateEmail() throws Exception {
    var email = "duplicate@email.com";
    var user = User.builder()
        .email(email)
        .password(passwordEncoder.encode("pass123"))
        .username("existing")
        .build();
    userRepository.save(user);

    var request = new RegisterUserRequest(email, "pass456", "newuser", null);

    mockMvc.perform(post("/auth/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().is4xxClientError());
  }

  @Test
  @DisplayName("Should authenticate and return JWT token")
  void shouldLoginSuccessfullyAndReturnToken() throws Exception {
    var rawPassword = "mypassword";
    var email = "login@test.com";

    var user = User.builder()
        .email(email)
        .username("loginuser")
        .password(passwordEncoder.encode(rawPassword))
        .build();
    userRepository.save(user);

    var loginRequest = new LoginRequest(email, rawPassword);

    mockMvc.perform(post("/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(loginRequest)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.token").exists());
  }


}
