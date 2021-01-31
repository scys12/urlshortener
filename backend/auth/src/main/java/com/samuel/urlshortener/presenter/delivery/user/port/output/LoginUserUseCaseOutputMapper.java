package com.samuel.urlshortener.presenter.delivery.user.port.output;

import com.samuel.urlshortener.core.usecase.user.LoginUserUseCase;
import com.samuel.urlshortener.presenter.delivery.entities.AuthenticationResponse;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class LoginUserUseCaseOutputMapper {
    public static ResponseEntity<AuthenticationResponse> map(LoginUserUseCase.OutputValues outputValues) {
        Map response = new HashMap();
        response.put("user", outputValues.getUser());
        response.put("token", outputValues.getJwtToken());
        return ResponseEntity.ok(new AuthenticationResponse(response));
    }
}
