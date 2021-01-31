package com.samuel.urlshortener.data.persistent.mongodb.repositories.impl;

import com.samuel.urlshortener.core.domain.User;
import com.samuel.urlshortener.data.persistent.mongodb.entities.UserData;
import com.samuel.urlshortener.data.persistent.mongodb.repositories.MongoUserRepository;
import com.samuel.urlshortener.data.persistent.mongodb.repositories.contracts.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final MongoUserRepository mongoUserRepository;

    public UserRepositoryImpl(MongoUserRepository mongoUserRepository) {
        this.mongoUserRepository = mongoUserRepository;
    }

    @Override
    public boolean existsByEmail(String email) {
        return mongoUserRepository.existsByEmail(email);
    }

    @Override
    public Optional<UserData> findByEmail(String email) {
        return mongoUserRepository.findByEmail(email);
    }

    @Override
    public Optional<UserData> findById(String id) {
        return mongoUserRepository.findById(id);
    }

    @Override
    public User registerUser(User user) {
        final UserData userData = UserData.newInstance(user);
        return mongoUserRepository.save(userData).toUser();
    }
}
