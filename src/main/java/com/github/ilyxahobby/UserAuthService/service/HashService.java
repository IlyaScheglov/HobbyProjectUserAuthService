package com.github.ilyxahobby.UserAuthService.service;

import de.mkammerer.argon2.Argon2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HashService {

    private static final Integer ITERATIONS = 22;
    private static final Integer MEMORY = 65536;
    private static final Integer PARALLELISM = 1;

    private final Argon2 argon2;

    public String hash(String password) {
        return argon2.hash(ITERATIONS, MEMORY, PARALLELISM, password.toCharArray());
    }

    public Boolean verify(String enteredPassword, String passwordInDB) {
        return argon2.verify(passwordInDB, enteredPassword.toCharArray());
    }
}
