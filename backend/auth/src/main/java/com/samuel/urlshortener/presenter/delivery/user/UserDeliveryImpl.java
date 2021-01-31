package com.samuel.urlshortener.presenter.delivery.user;

import com.samuel.urlshortener.core.usecase.UseCaseExecutor;
import com.samuel.urlshortener.core.usecase.user.RegisterUserUseCase;
import com.samuel.urlshortener.presenter.delivery.entities.ApiResponse;
import com.samuel.urlshortener.presenter.delivery.entities.AuthenticationResponse;
import com.samuel.urlshortener.presenter.delivery.entities.LoginUserRequest;
import com.samuel.urlshortener.presenter.delivery.entities.RegisterUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@Component
public class UserDeliveryImpl implements UserDelivery {
    @Autowired
    private UseCaseExecutor useCaseExecutor;
    @Autowired
    private RegisterUserUseCase registerUserUseCase;
    @Autowired
    private RegisterUserUseCaseInputMapper registerUserUseCaseInputMapper;


    @Override
    public CompletableFuture<ResponseEntity<ApiResponse>> registerUser(@Valid RegisterUserRequest request, HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                registerUserUseCase,
                registerUserUseCaseInputMapper.map(request),
                (outputValues -> RegisterUserUseCaseOutputMapper.map(outputValues.getUser(), httpServletRequest))
        );
    }

    @Override
    public CompletableFuture<ResponseEntity<AuthenticationResponse>> loginUser(@Valid LoginUserRequest request) {
        return null;
    }
}
