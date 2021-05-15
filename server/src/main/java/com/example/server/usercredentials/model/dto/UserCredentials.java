package com.example.server.usercredentials.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static com.example.server.usercredentials.utils.ExceptionsMessages.EMPTY_FIELD;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentials implements Serializable {
    @NotBlank(message = EMPTY_FIELD)
    @Pattern(message = "Incorrect login", regexp = "^[a-zA-Z](.[a-zA-Z0-9_-]*)$")
    private  String login;
    @NotBlank(message = EMPTY_FIELD)
    @Pattern(message = "Incorrect password",regexp = "[0-9a-zA-Z]{6,}")
    private  String password;
    @NotBlank(message = EMPTY_FIELD)
    private String email;
    @NotBlank(message = EMPTY_FIELD)
    private String secretKey;

}
