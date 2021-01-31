package com.samuel.urlshortener.presenter.delivery.entities;

import lombok.Value;

@Value
public class AuthenticationResponse {
    private boolean success = true;
    private String token;
}