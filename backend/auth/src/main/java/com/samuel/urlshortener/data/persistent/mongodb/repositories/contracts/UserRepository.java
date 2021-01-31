package com.samuel.urlshortener.data.persistent.mongodb.repositories.contracts;

import com.samuel.urlshortener.core.domain.User;
import com.samuel.urlshortener.data.persistent.mongodb.entities.UserData;

import java.util.Optional;

public interface UserRepository {
    boolean existsByEmail(String email);
    Optional<UserData> findByEmail(String email);
    Optional<UserData> findById(String id);
    User registerUser(User user);

}
