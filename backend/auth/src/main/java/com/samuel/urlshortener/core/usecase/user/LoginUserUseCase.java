package com.samuel.urlshortener.core.usecase.user;

import com.samuel.urlshortener.core.domain.User;
import com.samuel.urlshortener.core.exception.DomainException;
import com.samuel.urlshortener.core.exception.NotFoundException;
import com.samuel.urlshortener.core.usecase.UseCase;
import com.samuel.urlshortener.data.persistent.mongodb.repositories.contracts.UserRepository;
import com.samuel.urlshortener.presenter.usecase.security.CustomUserDetailsService;
import com.samuel.urlshortener.presenter.usecase.security.JwtProvider;
import com.samuel.urlshortener.presenter.usecase.security.UserPrincipal;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class LoginUserUseCase extends UseCase<LoginUserUseCase.InputValues, LoginUserUseCase.OutputValues>{
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserRepository userRepository;

    @Override
    public OutputValues execute(InputValues input) {
        Authentication authentication = authenticationManager.authenticate(input.authenticationToken);
        if (!authentication.isAuthenticated())
            throw new AuthenticationException("Email/Password is invalid") {
            };
        var token = jwtProvider.generateToken(authentication);
        var userDetails = (UserPrincipal) authentication.getPrincipal();
        var user = userRepository.findByEmail(userDetails.getEmail()).get();
        return new OutputValues(user.toUser(),token);
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        private final UsernamePasswordAuthenticationToken authenticationToken;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final User user;
        private final String jwtToken;
    }
}
