package com.github.ilyxahobby.UserAuthService.dto;

import com.github.ilyxahobby.UserAuthService.util.validation.annotation.StrongPassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public abstract class AbstractUserDto {

    @NotBlank
    @Size(max = 50, message = "Логин слишком большой или пустой")
    private String login;

    @StrongPassword(message = "Пароль должен быть минимум 8 символов, также содержать цифры и латинские буквы")
    private String password;
}
