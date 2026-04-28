package com.usersLogin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.usersLogin.dto.UserLoginDTO;
import com.usersLogin.dto.UserRegisterDTO;
import com.usersLogin.model.User;
import com.usersLogin.service.CredentialsValidator;
import com.usersLogin.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;
    private final CredentialsValidator validator;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserController(UserService service, CredentialsValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @PostMapping("/register")
    public ResponseEntity<User> create(@RequestBody UserRegisterDTO dto) {
        this.validator.validatePassword(dto.getEmail(), dto.getPassword());
        User user = new User(dto.getFirstName(), dto.getLastName(), dto.getEmail(), passwordEncoder.encode(dto.getPassword()));
        User created = service.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/login")
    public ResponseEntity<User> create(@RequestBody UserLoginDTO dto) {
        validator.areValidCredentials(dto.getEmail(), passwordEncoder.encode(dto.getPassword()))
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
