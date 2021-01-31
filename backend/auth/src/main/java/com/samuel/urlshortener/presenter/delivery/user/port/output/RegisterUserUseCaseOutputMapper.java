package com.samuel.urlshortener.presenter.delivery.user.port.output;

import com.samuel.urlshortener.core.domain.User;
import com.samuel.urlshortener.presenter.delivery.entities.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

public final class RegisterUserUseCaseOutputMapper {
    public static ResponseEntity<ApiResponse> map(User user, HttpServletRequest httpServletRequest) {
        URI location = ServletUriComponentsBuilder
                .fromContextPath(httpServletRequest)
                .path("/user/{id}")
                .buildAndExpand(user.getUserId())
                .toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "registered successfully"));
    }
}
