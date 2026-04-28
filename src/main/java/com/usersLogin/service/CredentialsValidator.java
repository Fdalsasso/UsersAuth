package com.usersLogin.service;

import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.DefaultPasswordValidator;
import org.passay.ValidationResult;
import org.passay.data.EnglishCharacterData;
import org.passay.rule.CharacterRule;
import org.passay.rule.LengthRule;
import org.passay.rule.UsernameRule;
import org.passay.rule.WhitespaceRule;

import com.usersLogin.exception.InvalidPasswordException;
import com.usersLogin.exception.WeakPasswordException;
import com.usersLogin.model.User;

import java.util.List;

public class CredentialsValidator {

    private final PasswordValidator validator;
    private final UserService service;

    public CredentialsValidator(UserService service) {
        validator = new DefaultPasswordValidator(
            new LengthRule(8, 128),
            new CharacterRule(EnglishCharacterData.UpperCase, 1),
            new CharacterRule(EnglishCharacterData.LowerCase, 1),
            new CharacterRule(EnglishCharacterData.Digit, 1),
            new CharacterRule(EnglishCharacterData.Special, 1),
            new WhitespaceRule(),
            new UsernameRule()
        );
        this.service = service;
    }

    /**
     * Validates the password and returns a single formatted string of errors.
     *
     * @param username The user's username
     * @param password The password to validate
     * @return A single string containing all errors, or an empty string if valid
     */
    public String validatePassword(String username, String password) {
        PasswordData passwordData = new PasswordData(username, password);

        ValidationResult result = validator.validate(passwordData);

        if (result.isValid()) {
            return "";
        }
        
        List<String> messages = result.getMessages();
        throw new WeakPasswordException(String.join("\n", messages));
    }

    public boolean areValidCredentials(String email, String hashedPassword) {
        User user = service.findByEmail(email);
        if (user.getHashedPassword().equals(hashedPassword)) { return true; }
        throw new InvalidPasswordException(email);
    }
}