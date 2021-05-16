package com.example.server.usercredentials.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationDto {
    @NotBlank
    String login;
    @NotBlank
    String password;
}
