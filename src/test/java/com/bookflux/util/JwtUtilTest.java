package com.bookflux.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bookflux.utils.JwtUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = JwtUtil.class)
@TestPropertySource(properties = {
    "application.security.jwt.secret-key=GHwSkQ5UYlV30rLX6Fuw3ILNxxTHQkm0FY4BGOpbBoDi4DLcg8dACvSZB+w/gJsbaMvTvdd/u3pG8CThArAGGQ=="})
class JwtUtilTest {

  @Autowired
  private JwtUtil jwtUtil;

  @DisplayName("Should generate correct token for given email")
  @Test
  void shouldGenerateCorrectTokenForGivenEmail() {
    var email = "felipe@email.com";
    var expectedToken = "eyJhbGciOiJIUzUxMiJ9";

    var token = jwtUtil.generateToken(email);
    assertNotNull(token);
    assertTrue(token.contains(expectedToken));
  }

  @DisplayName("Should correctly extract email from token")
  @Test
  void shouldCorrectlyExtractEmailFromToken() {
    var expectedEmail = "felipe@email.com";
    var token = jwtUtil.generateToken(expectedEmail);

    var extractedEmail = jwtUtil.extractEmail(token);
    assertEquals(expectedEmail, extractedEmail);
  }

  @DisplayName("Should successfully validate token when its valid")
  @Test
  void shouldSuccessfullyValidateTokenWhenItsValid() {
    var email = "felipe@email.com";
    var token = jwtUtil.generateToken(email);

    var isTokenValid = jwtUtil.validateToken(token, email);
    assertTrue(isTokenValid);
  }

  @DisplayName("Token should be invalid when email and token are not related")
  @Test
  void tokenShouldBeInvalidWhenEmailAndTokenAreNotRelated() {
    var email = "felipe@email.com";
    var token = jwtUtil.generateToken("test@email.com");

    var isValidToken = jwtUtil.validateToken(token, email);
    assertFalse(isValidToken);
  }
}
