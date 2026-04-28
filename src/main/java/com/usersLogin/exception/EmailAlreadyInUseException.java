package com.usersLogin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmailAlreadyInUseException extends ResponseStatusException {

    private final String email;

    public EmailAlreadyInUseException(String email) {
        super(HttpStatus.CONFLICT, "Email `" + email + "` already in use.");
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
