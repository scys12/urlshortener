package com.samuel.urlshortener.data.persistent.mongodb.repositories.contracts;

import com.samuel.urlshortener.data.persistent.mongodb.entities.EmailVerificationData;
import com.samuel.urlshortener.data.persistent.mongodb.entities.UserData;

import java.util.Optional;

public interface EmailVerificationRepository {
    Optional<EmailVerificationData> getVerificationToken(String token);
    public void createVerificationToken(UserData user, String token);
}
