package com.usersLogin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {

    private final String email;

    public UserNotFoundException(String email) {
        super(HttpStatus.NOT_FOUND, "User not found with email: " + email);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
