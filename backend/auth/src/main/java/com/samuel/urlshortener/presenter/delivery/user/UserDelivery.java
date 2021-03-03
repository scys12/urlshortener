package com.samuel.urlshortener.presenter.delivery.user;

import com.samuel.urlshortener.presenter.delivery.entities.ApiResponse;
import com.samuel.urlshortener.presenter.delivery.entities.AuthenticationResponse;
import com.samuel.urlshortener.presenter.delivery.entities.LoginUserRequest;
import com.samuel.urlshortener.presenter.delivery.entities.RegisterUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
public interface UserDelivery {
    @PostMapping("/register")
    CompletableFuture<ResponseEntity<ApiResponse>> registerUser(
            @Valid @RequestBody RegisterUserRequest request, HttpServletRequest httpServletRequest);

    @PostMapping("/login")
    CompletableFuture<ResponseEntity<AuthenticationResponse>> loginUser(
            @Valid @RequestBody LoginUserRequest request);

    @GetMapping("/email/verify")
    CompletableFuture<ResponseEntity<ApiResponse>> verifyAccount(
            @Valid @RequestParam("token") String token, HttpServletRequest httpServletRequest);
}
