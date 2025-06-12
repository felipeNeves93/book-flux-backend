package com.bookflux.service;

import com.bookflux.exception.UserAlreadyExistsException;
import com.bookflux.repository.collection.user.User;
import com.bookflux.repository.collection.user.UserRepository;
import com.bookflux.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  @Transactional
  public void registerUser(User user) {
    var existingUser = userRepository.findByEmail(user.getEmail()).orElse(null);

    if (existingUser != null) {
      throw new UserAlreadyExistsException(
          "User with email " + user.getEmail() + " already exists");
    }

    var existingUsername = userRepository.findByUsername(user.getUsername()).orElse(null);

    if (existingUsername != null) {
      throw new UserAlreadyExistsException(
          "User with username " + user.getUsername() + " already exists");
    }

    user.setId(StringUtils.generateUUID());
    userRepository.save(user);
  }

}
