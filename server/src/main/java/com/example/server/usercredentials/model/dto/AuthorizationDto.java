package com.example.server.usercredentials.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationDto {
    @NotBlank(message = "Empty login")
    String login;
    @NotBlank(message = "Empty password")
    String password;
}
