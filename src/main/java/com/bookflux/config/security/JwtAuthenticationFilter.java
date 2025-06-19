package com.bookflux.config.security;

import com.bookflux.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

@Service
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtUtil jwtUtil;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain)
      throws ServletException, IOException {
    var authHeader = request.getHeader("Authorization");
    String jwt = null;
    String email = null;

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      jwt = authHeader.substring(7);
      email = jwtUtil.extractEmail(jwt);
    }

    if (this.isSecurityContextValid(email, jwt)) {
      UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
          email, null, Collections.emptyList());
      authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
      SecurityContextHolder.getContext().setAuthentication(authToken);

    }

    chain.doFilter(request, response);
  }

  private boolean isSecurityContextValid(String email, String jwt) {
    return (email != null && SecurityContextHolder.getContext().getAuthentication() == null
        && jwtUtil.validateToken(jwt, email));

  }

}
