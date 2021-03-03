package com.samuel.urlshortener.core.usecase.user;

import com.samuel.urlshortener.core.domain.User;
import com.samuel.urlshortener.core.exception.NotFoundException;
import com.samuel.urlshortener.core.usecase.UseCase;
import com.samuel.urlshortener.data.persistent.mongodb.entities.EmailVerificationData;
import com.samuel.urlshortener.data.persistent.mongodb.entities.UserData;
import com.samuel.urlshortener.data.persistent.mongodb.repositories.contracts.EmailVerificationRepository;
import com.samuel.urlshortener.data.persistent.mongodb.repositories.contracts.UserRepository;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class VerifyAccountUseCase extends UseCase<VerifyAccountUseCase.InputValues, VerifyAccountUseCase.OutputValues>{
    @Autowired
    private EmailVerificationRepository emailVerificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public OutputValues execute(InputValues input) {
        Optional<EmailVerificationData> verificationData;
        verificationData = emailVerificationRepository.getVerificationToken(input.getToken());
        if(verificationData.filter(
                emailVerificationData -> emailVerificationData.getUser().isEnabled()
                && new Date().compareTo(emailVerificationData.getExpiryDate()) <= 0 )
                .isPresent()){
            throw new NotFoundException("Akun telah diaktivasi");
        }
        verificationData.filter(emailVerificationData -> new Date().compareTo(emailVerificationData.getExpiryDate()) <= 0).
                map(emailVerificationData -> {
                    User user = emailVerificationData.getUser().toExistingUser();
                    user.setEnabled(true);
                    return userRepository.registerUser(user);
                }).
                orElseThrow(() -> new NotFoundException("Token tidak ditemukan"));
        return new OutputValues();
    }

    @Value
    public static class InputValues implements UseCase.InputValues{
        private final String token;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
    }
}
