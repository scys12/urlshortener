package com.samuel.urlshortener.core.usecase.user;

import com.samuel.urlshortener.core.domain.User;
import com.samuel.urlshortener.core.exception.EmailExistException;
import com.samuel.urlshortener.core.usecase.UseCase;
import com.samuel.urlshortener.data.persistent.mongodb.repositories.contracts.UserRepository;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserUseCase extends UseCase<RegisterUserUseCase.InputValues, RegisterUserUseCase.OutputValues> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OutputValues execute(InputValues input) {
        if (userRepository.existsByEmail(input.getEmail())) {
            throw new EmailExistException("Email already in use!");
        }
        User user = User.newInstance(input.getName(), input.getUsername(), input.getPassword(), input.getEmail());
        return new OutputValues(userRepository.registerUser(user));
    }

    @Value
    public static class InputValues implements UseCase.InputValues{
        private final String name;
        private final String email;
        private final String username;
        private final String password;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final User user;
    }
}
