package com.samuel.urlshortener.data.persistent.mongodb.repositories.impl;

import com.samuel.urlshortener.data.persistent.mongodb.entities.EmailVerificationData;
import com.samuel.urlshortener.data.persistent.mongodb.entities.UserData;
import com.samuel.urlshortener.data.persistent.mongodb.repositories.MongoEmailVerificationRepository;
import com.samuel.urlshortener.data.persistent.mongodb.repositories.contracts.EmailVerificationRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class EmailVerificationRepositoryImpl implements EmailVerificationRepository {
    private final MongoEmailVerificationRepository mongoEmailVerificationRepository;

    public EmailVerificationRepositoryImpl(MongoEmailVerificationRepository mongoEmailVerificationRepository) {
        this.mongoEmailVerificationRepository = mongoEmailVerificationRepository;
    }
    @Override
    public Optional<EmailVerificationData> getVerificationToken(String verificationToken) {
        return mongoEmailVerificationRepository.findByToken(verificationToken);
    }

    @Override
    public void createVerificationToken(UserData user, String token) {
        EmailVerificationData myToken = EmailVerificationData.newInstance(token, user);
        mongoEmailVerificationRepository.save(myToken);
    }
}
