package com.usersLogin.service;

import com.usersLogin.exception.UserNotFoundException;
import com.usersLogin.model.User;
import com.usersLogin.repository.UserRepository;

public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        return repository.save(user);
    }

    public User findByEmail(String email) {
        User user = repository.findByEmail(email);
        if (user == null) { throw new UserNotFoundException(email); }
        return user;
    }
}