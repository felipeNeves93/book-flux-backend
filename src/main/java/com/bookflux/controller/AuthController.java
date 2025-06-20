package com.bookflux.controller;

import com.bookflux.config.tenant.TenantContext;
import com.bookflux.controller.requests.LoginRequest;
import com.bookflux.controller.requests.RegisterUserRequest;
import com.bookflux.controller.response.AuthResponse;
import com.bookflux.repository.collection.user.User;
import com.bookflux.service.UserService;
import com.bookflux.utils.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;

  @PostMapping("/register")
  public ResponseEntity<String> registerUser(
      @RequestBody @Valid RegisterUserRequest registerUserRequest) {
    var userToRegister = User.builder().email(registerUserRequest.email())
        .profilePicture(registerUserRequest.profilePicture())
        .username(registerUserRequest.username())
        .password(passwordEncoder.encode(registerUserRequest.password())).build();

    userService.registerUser(userToRegister);

    return ResponseEntity.ok("User created successfully!");
  }

  @PostMapping("/login")
  public ResponseEntity<?> loginUser(@RequestBody @Valid LoginRequest loginRequest) {
    var authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    var token = jwtUtil.generateToken(authentication.getName());

    return ResponseEntity.ok(new AuthResponse(token, authentication.getName()));
  }

  @GetMapping("/oauth2/success")
  public ResponseEntity<?> oauth2Success(Authentication authentication) {
    var jwt = jwtUtil.generateToken(authentication.getName());
    return ResponseEntity.ok(new AuthResponse(jwt, authentication.getName()));
  }

  @PostMapping("/logout")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<String> logout() {
    SecurityContextHolder.clearContext();
    TenantContext.clear();

    return ResponseEntity.ok("Logout successfully!");
  }
}
