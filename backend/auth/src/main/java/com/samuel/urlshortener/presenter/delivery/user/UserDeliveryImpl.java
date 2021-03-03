package com.samuel.urlshortener.presenter.delivery.user;

import com.samuel.urlshortener.core.usecase.UseCaseExecutor;
import com.samuel.urlshortener.core.usecase.user.LoginUserUseCase;
import com.samuel.urlshortener.core.usecase.user.RegisterUserUseCase;
import com.samuel.urlshortener.core.usecase.user.VerifyAccountUseCase;
import com.samuel.urlshortener.presenter.delivery.entities.ApiResponse;
import com.samuel.urlshortener.presenter.delivery.entities.AuthenticationResponse;
import com.samuel.urlshortener.presenter.delivery.entities.LoginUserRequest;
import com.samuel.urlshortener.presenter.delivery.entities.RegisterUserRequest;
import com.samuel.urlshortener.presenter.delivery.user.port.input.LoginUserUseCaseInputMapper;
import com.samuel.urlshortener.presenter.delivery.user.port.input.RegisterUserUseCaseInputMapper;
import com.samuel.urlshortener.presenter.delivery.user.port.input.VerifyAccountUseCaseInputMapper;
import com.samuel.urlshortener.presenter.delivery.user.port.output.LoginUserUseCaseOutputMapper;
import com.samuel.urlshortener.presenter.delivery.user.port.output.RegisterUserUseCaseOutputMapper;
import com.samuel.urlshortener.presenter.delivery.user.port.output.VerifyAccountUseCaseOutputMapper;
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
    private LoginUserUseCase loginUserUseCase;

    @Autowired
    private VerifyAccountUseCase verifyAccountUseCase;

    @Autowired
    private RegisterUserUseCaseInputMapper registerUserUseCaseInputMapper;

    @Override
    public CompletableFuture<ResponseEntity<ApiResponse>> registerUser(@Valid RegisterUserRequest request, HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                registerUserUseCase,
                registerUserUseCaseInputMapper.map(request, httpServletRequest),
                (outputValues -> RegisterUserUseCaseOutputMapper.map(outputValues.getUser(), httpServletRequest))
        );
    }

    @Override
    public CompletableFuture<ResponseEntity<AuthenticationResponse>> loginUser(@Valid LoginUserRequest request) {
        return useCaseExecutor.execute(
                loginUserUseCase,
                LoginUserUseCaseInputMapper.map(request),
                LoginUserUseCaseOutputMapper::map
        );
    }

    @Override
    public CompletableFuture<ResponseEntity<ApiResponse>> verifyAccount(@Valid String token, HttpServletRequest request) {
        return useCaseExecutor.execute(
            verifyAccountUseCase,
            VerifyAccountUseCaseInputMapper.map(token),
            (outputValues -> VerifyAccountUseCaseOutputMapper.map(request))
        );
    }
}
