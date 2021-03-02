package com.samuel.urlshortener.presenter.delivery.user.port.input;

import com.samuel.urlshortener.core.usecase.user.RegisterUserUseCase;
import com.samuel.urlshortener.presenter.delivery.entities.RegisterUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public final class RegisterUserUseCaseInputMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegisterUserUseCase.InputValues map(RegisterUserRequest request, HttpServletRequest httpServletRequest){
        int serverPort = httpServletRequest.getServerPort();
        StringBuilder url = new StringBuilder();
        url.append(httpServletRequest.getScheme()).append("://").append(httpServletRequest.getServerName());
        if (serverPort != 80 && serverPort != 443) {
            url.append(":").append(serverPort);
        }
        url.append(httpServletRequest.getContextPath());
        return new RegisterUserUseCase.InputValues(request.getName(), request.getEmail(), request.getUsername(), passwordEncoder.encode(request.getPassword()), url.toString());
    }
}
