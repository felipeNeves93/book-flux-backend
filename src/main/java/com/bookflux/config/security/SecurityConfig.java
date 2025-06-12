package com.bookflux.config.security;

import com.bookflux.repository.collection.user.UserRepository;
import com.bookflux.util.JwtUtil;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final CustomOauth2UserService customOAuth2UserService;
  private final JwtUtil jwtUtil;

  public SecurityConfig(CustomOauth2UserService customOAuth2UserService, JwtUtil jwtUtil) {
    this.customOAuth2UserService = customOAuth2UserService;
    this.jwtUtil = jwtUtil;
  }

  @Bean
  @Profile("!local")
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/register", "/login", "/oauth2/**", "/oauth2/success").permitAll()
            .anyRequest().authenticated()
        )
        .oauth2Login(oauth2 -> oauth2
            .userInfoEndpoint(userInfo -> userInfo
                .userService(customOAuth2UserService))
            .successHandler(
                (request, response, authentication) -> response.sendRedirect("/oauth2/success"))
        )
        .addFilterBefore(new JwtAuthenticationFilter(jwtUtil),
            UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  @Profile("local")
  public SecurityFilterChain securityFilterChainLocal(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/register", "/login").permitAll()
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .anyRequest().authenticated()
        )
        .addFilterBefore(new JwtAuthenticationFilter(jwtUtil),
            UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService(UserRepository userRepository) {
    return username -> {
      var user = userRepository.findByEmail(username)
          .orElseGet(() -> userRepository.findByUsername(username)
              .orElseThrow(() -> new UsernameNotFoundException("User not found")));
      return new org.springframework.security.core.userdetails.User(
          user.getEmail(),
          user.getPassword(),
          Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
      ) {
        public Map<String, Object> getAttributes() {
          Map<String, Object> attributes = new HashMap<>();
          attributes.put("email", user.getEmail());
          attributes.put("username", user.getUsername());
          attributes.put("profilePicture", user.getProfilePicture());
          return attributes;
        }
      };
    };
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig)
      throws Exception {
    return authConfig.getAuthenticationManager();
  }
}