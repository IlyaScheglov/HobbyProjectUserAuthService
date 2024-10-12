package com.github.ilyxahobby.UserAuthService.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class RegistrationUserDto extends AbstractUserDto {

    @NotNull
    private UUID secretInviteKey;
}
