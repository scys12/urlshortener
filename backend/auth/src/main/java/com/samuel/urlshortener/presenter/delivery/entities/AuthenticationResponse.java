package com.samuel.urlshortener.presenter.delivery.entities;

import lombok.Value;
import java.util.List;
import java.util.Map;

@Value
public class AuthenticationResponse {
    private boolean success = true;
    private Map data;
}