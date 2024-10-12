package com.github.ilyxahobby.UserAuthService.service;

import com.github.ilyxahobby.UserAuthService.dto.LoginUserDto;
import com.github.ilyxahobby.UserAuthService.dto.RegistrationUserDto;
import com.github.ilyxahobby.UserAuthService.entity.User;
import com.github.ilyxahobby.UserAuthService.repository.KeyRepository;
import com.github.ilyxahobby.UserAuthService.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final HashService hashService;
    private final JwtService jwtService;
    private final KeyRepository keyRepository;
    private final UserRepository userRepository;

    @Transactional
    public String registerNewUser(RegistrationUserDto registrationUserDto) {
        var key = keyRepository.findById(registrationUserDto.getSecretInviteKey())
                .orElseThrow(() -> new RuntimeException("404 key not found"));
        if (!key.getActive()) {
            throw new RuntimeException("415 conflict кто-то уже использовал этот ключ");
        }

        key.setActive(false);
        keyRepository.save(key);

        var newUser = User.builder()
                .login(registrationUserDto.getLogin())
                .password(hashService.hash(registrationUserDto.getPassword()))
                .build();
        var savedUser = userRepository.saveAndFlush(newUser);

        return jwtService.generateJwt(savedUser.getId(), savedUser.getLogin());
    }

    public String loginUser(LoginUserDto loginUserDto) {
        var user = userRepository.findByLogin(loginUserDto.getLogin())
                .orElseThrow(() -> new RuntimeException("404 user not found"));
        if (!hashService.verify(loginUserDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("401 bad password");
        }

        return jwtService.generateJwt(user.getId(), user.getLogin());
    }

    public UUID tryDoRequest(String token) {
        return jwtService.getIdFromToken(token);
    }
}
