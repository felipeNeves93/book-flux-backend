package com.bookflux.config.security;

import com.bookflux.repository.collection.user.User;
import com.bookflux.repository.collection.user.UserRepository;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomOauth2UserService extends DefaultOAuth2UserService {

  private final UserRepository userRepository;

  @Override
  @Transactional
  public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) {
    var oauth2User = super.loadUser(oAuth2UserRequest);
    var email = Objects.requireNonNull(oauth2User.getAttribute("email")).toString();
    var username = Objects.requireNonNull(oauth2User.getAttribute("name")).toString();
    var profilePicture = Objects.requireNonNull(oauth2User.getAttribute("picture")).toString();

    var user = userRepository.findByEmail(email).orElse(new User());
    user.setEmail(email);
    user.setUsername(username != null ? username : email.split("@")[0]);
    user.setProfilePicture(profilePicture);

    if (user.getPassword() == null) {
      user.setPassword("OAUTH2_USER");
    }
    userRepository.save(user);

    var attributes = new HashMap<String, Object>();

    attributes.put("username", username);
    attributes.put("profilePicture", profilePicture);

    return new DefaultOAuth2User(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")),
        attributes, "email");
  }

}
