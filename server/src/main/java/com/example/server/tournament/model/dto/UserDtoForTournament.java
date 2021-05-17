package com.example.server.tournament.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import static com.example.server.usercredentials.utils.constants.ExceptionsMessages.EMPTY_FIELD;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoForTournament {
    @NotBlank(message = EMPTY_FIELD)
    public String login;
    @NotBlank(message = EMPTY_FIELD)
    public String nameTournament;
}
