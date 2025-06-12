package com.bookflux.controller.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterUserRequest(@NotBlank @Email String email,
                                  @NotBlank String username,
                                  @NotBlank String password,
                                  String profilePicture) {

}
