package com.example.server.usercredentials.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPassDto {
    @NotBlank
    private String login;
    @NotBlank
    private String secretKey;
    @NotBlank
    private String newPassword;
}
