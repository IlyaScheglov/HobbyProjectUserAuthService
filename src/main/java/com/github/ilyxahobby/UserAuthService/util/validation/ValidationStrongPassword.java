package com.github.ilyxahobby.UserAuthService.util.validation;

import com.github.ilyxahobby.UserAuthService.util.validation.annotation.StrongPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidationStrongPassword implements ConstraintValidator<StrongPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasDigit = false;
        boolean hasLatinChar = false;

        for (char c : password.toCharArray()) {
            if (hasDigit && hasLatinChar) {
                return true;
            }
            if(Character.isDigit(c)) {
                hasDigit = true;
                continue;
            }
            if (Character.isLetter(c)) {
                hasLatinChar = true;
            }
        }

        return hasDigit && hasLatinChar;
    }
}
