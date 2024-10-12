package com.github.ilyxahobby.UserAuthService.controller;

import com.github.ilyxahobby.UserAuthService.dto.LoginUserDto;
import com.github.ilyxahobby.UserAuthService.dto.RegistrationUserDto;
import com.github.ilyxahobby.UserAuthService.service.AuthService;
import com.github.ilyxahobby.UserAuthService.util.Constants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constants.API_V1_PREFIX + "/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registration")
    public String registerNewUser(@RequestBody @Valid RegistrationUserDto registrationUserDto) {
        return authService.registerNewUser(registrationUserDto);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody @Valid LoginUserDto loginUserDto) {
        return authService.loginUser(loginUserDto);
    }

    @GetMapping
    public UUID tryDoRequest(@RequestHeader String jwtToken) {
        return authService.tryDoRequest(jwtToken);
    }
}
