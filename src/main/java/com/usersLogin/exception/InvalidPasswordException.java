package com.usersLogin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidPasswordException extends ResponseStatusException {

    private final String email;

    public InvalidPasswordException(String email) {
        super(HttpStatus.UNAUTHORIZED, "Invalid password for user with email: " + email);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
