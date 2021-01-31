package com.samuel.urlshortener.data.persistent.mongodb.repositories;

import com.samuel.urlshortener.data.persistent.mongodb.entities.UserData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MongoUserRepository extends MongoRepository<UserData, String> {
    boolean existsByEmail(String email);
    Optional<UserData> findByEmail(String email);
}
