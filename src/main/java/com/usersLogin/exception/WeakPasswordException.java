package com.usersLogin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class WeakPasswordException extends ResponseStatusException {
    private final String passwordDetails;

    public WeakPasswordException(String passwordDetails) {
        super(HttpStatus.BAD_REQUEST, "The password must: " + passwordDetails);
        this.passwordDetails = passwordDetails;
    }

    public String getPasswordDetails() {
        return passwordDetails;
    }
}
