package com.github.ilyxahobby.UserAuthService.util.validation.annotation;

import com.github.ilyxahobby.UserAuthService.util.validation.ValidationStrongPassword;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidationStrongPassword.class)
public @interface StrongPassword {

    String message() default "Строка неправильной длины";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
