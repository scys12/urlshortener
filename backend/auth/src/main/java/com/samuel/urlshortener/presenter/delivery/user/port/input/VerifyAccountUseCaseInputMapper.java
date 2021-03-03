package com.samuel.urlshortener.presenter.delivery.user.port.input;

import com.samuel.urlshortener.core.usecase.user.VerifyAccountUseCase;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public final class VerifyAccountUseCaseInputMapper {
    public static VerifyAccountUseCase.InputValues map(String token) {
        return new VerifyAccountUseCase.InputValues(token);
    }
}
