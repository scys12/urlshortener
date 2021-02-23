package com.samuel.urlshortener.data.persistent.mongodb.repositories.contracts;

import com.samuel.urlshortener.data.persistent.mongodb.entities.EmailVerificationData;
import com.samuel.urlshortener.data.persistent.mongodb.entities.UserData;

public interface EmailVerificationRepository {
    public EmailVerificationData getVerificationToken(String token);
    public void createVerificationToken(UserData user, String token);
}
