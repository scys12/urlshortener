package com.samuel.urlshortener.presenter.delivery.entities;

import lombok.Value;

@Value
public class ApiResponse {
    private final Boolean status;
    private final String message;
}