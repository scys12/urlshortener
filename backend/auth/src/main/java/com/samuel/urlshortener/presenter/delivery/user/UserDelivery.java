package com.samuel.urlshortener.presenter.delivery.user;

import com.samuel.urlshortener.presenter.delivery.entities.ApiResponse;
import com.samuel.urlshortener.presenter.delivery.entities.AuthenticationResponse;
import com.samuel.urlshortener.presenter.delivery.entities.LoginUserRequest;
import com.samuel.urlshortener.presenter.delivery.entities.RegisterUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/user")
public interface UserDelivery {
    @PostMapping("/register")
    CompletableFuture<ResponseEntity<ApiResponse>> registerUser(
            @Valid @RequestBody RegisterUserRequest request, HttpServletRequest httpServletRequest);

    @PostMapping("/login")
    CompletableFuture<ResponseEntity<AuthenticationResponse>> loginUser(
            @Valid @RequestBody LoginUserRequest request);
}
