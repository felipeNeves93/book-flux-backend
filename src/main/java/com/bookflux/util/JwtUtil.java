package com.bookflux.util;

import com.bookflux.exception.InvalidSecretKeyException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Base64;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

  @Value("${application.security.jwt.secret-key}")
  private String secretKey;

  public String generateToken(String email) {
    try {
      byte[] keyBytes = Base64.getDecoder().decode(secretKey);
      // 1 hour
      long expirationTime = 1000 * 60L * 60;
      return Jwts.builder()
          .setSubject(email)
          .setIssuedAt(new Date())
          .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
          .signWith(Keys.hmacShaKeyFor(keyBytes), SignatureAlgorithm.HS512)
          .compact();
    } catch (IllegalArgumentException e) {
      throw new InvalidSecretKeyException(
          "Invalid Base64-encoded JWT secret key: " + e.getMessage());
    }
  }

  public String extractEmail(String token) {
    return getClaims(token).getSubject();
  }

  public boolean validateToken(String token, String email) {
    final String extractedEmail = extractEmail(token);
    return (extractedEmail.equals(email) && !isTokenExpired(token));
  }

  private boolean isTokenExpired(String token) {
    return getClaims(token).getExpiration().before(new Date());
  }

  private Claims getClaims(String token) {
    try {
      byte[] keyBytes = Base64.getDecoder().decode(secretKey);
      return Jwts.parser()
          .setSigningKey(Keys.hmacShaKeyFor(keyBytes))
          .parseClaimsJws(token)
          .getBody();
    } catch (IllegalArgumentException e) {
      throw new InvalidSecretKeyException(
          "Invalid Base64-encoded JWT secret key: " + e.getMessage());
    }
  }
}