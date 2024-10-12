package com.github.ilyxahobby.UserAuthService.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class LoginUserDto extends AbstractUserDto {
}
