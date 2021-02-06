package com.samuel.urlshortener.presenter.delivery.entities;

import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
public class LoginUserRequest {

    @Email
    @NotBlank
    private final String email;

    @NotBlank
    private final String password;
}
