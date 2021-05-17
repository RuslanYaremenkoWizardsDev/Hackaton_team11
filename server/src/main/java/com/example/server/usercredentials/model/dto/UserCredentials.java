package com.example.server.usercredentials.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import static com.example.server.usercredentials.utils.constants.ExceptionsMessages.*;
import static com.example.server.usercredentials.utils.constants.RegExp.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentials implements Serializable {
    @NotBlank(message = EMPTY_FIELD)
    @Pattern(message = INCORRECT_LOGIN, regexp = REG_EXP_FOR_LOGIN)
    private String login;
    @NotBlank(message = EMPTY_FIELD)
    @Pattern(message = INCORRECT_PASSWORD, regexp = REG_EXP_FOR_PASSWORD)
    private String password;
    @NotBlank(message = EMPTY_FIELD)
    @Pattern(message = "Incorrect email", regexp = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$")
    private String email;
    @NotBlank(message = EMPTY_FIELD)
    private String secretKey;

}
