package com.example.server.usercredentials.model.dto;

import lombok.Value;
import javax.validation.constraints.NotBlank;

@Value
public class AuthorizationDto {
    @NotBlank
    String login;
    @NotBlank
    String password;
}
