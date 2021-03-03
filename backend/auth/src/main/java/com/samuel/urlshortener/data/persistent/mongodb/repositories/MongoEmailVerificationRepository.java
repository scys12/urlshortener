package com.samuel.urlshortener.data.persistent.mongodb.repositories;

import com.samuel.urlshortener.core.domain.User;
import com.samuel.urlshortener.data.persistent.mongodb.entities.EmailVerificationData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MongoEmailVerificationRepository extends MongoRepository<EmailVerificationData, String> {
    Optional<EmailVerificationData> findByToken(String token);
    EmailVerificationData findByUser(User user);
}
