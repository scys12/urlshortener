package com.samuel.urlshortener.presenter.delivery.user.port.input;

import com.samuel.urlshortener.core.exception.NotFoundException;
import com.samuel.urlshortener.core.usecase.user.LoginUserUseCase;
import com.samuel.urlshortener.presenter.delivery.entities.LoginUserRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public final class LoginUserUseCaseInputMapper {
    public static LoginUserUseCase.InputValues map(LoginUserRequest signInRequest) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                signInRequest.getEmail(),
                signInRequest.getPassword());
        return new LoginUserUseCase.InputValues(auth);
    }
}
