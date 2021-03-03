package com.samuel.urlshortener.presenter.delivery.user.port.output;

import com.samuel.urlshortener.presenter.delivery.entities.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

public final class VerifyAccountUseCaseOutputMapper {
    public static ResponseEntity<ApiResponse> map(HttpServletRequest httpServletRequest) {
        URI location = ServletUriComponentsBuilder
                .fromContextPath(httpServletRequest)
                .path("/login")
                .build()
                .toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "verification account successfully"));
    }
}
