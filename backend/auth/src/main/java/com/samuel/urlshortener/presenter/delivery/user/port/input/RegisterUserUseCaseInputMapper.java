package com.samuel.urlshortener.presenter.delivery.user.port.input;

import com.samuel.urlshortener.core.usecase.user.RegisterUserUseCase;
import com.samuel.urlshortener.presenter.delivery.entities.RegisterUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public final class RegisterUserUseCaseInputMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegisterUserUseCase.InputValues map(RegisterUserRequest request){
        return new RegisterUserUseCase.InputValues(request.getName(), request.getEmail(), request.getUsername(), passwordEncoder.encode(request.getPassword()));
    }
}
