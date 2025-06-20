package com.bookflux.config;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

@TestConfiguration
@Profile("test")
public class TestOauth2Config {

  @Bean
  public ClientRegistrationRepository clientRegistrationRepository() {
    return Mockito.mock(ClientRegistrationRepository.class);
  }

}
