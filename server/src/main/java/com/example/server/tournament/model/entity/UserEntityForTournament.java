package com.example.server.tournament.model.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;

import static com.example.server.usercredentials.utils.constants.ExceptionsMessages.EMPTY_FIELD;

@Data
public class UserEntityForTournament {
    @NotBlank(message = EMPTY_FIELD)
    public String login;
    @NotBlank(message = EMPTY_FIELD)
    public String nameTournament;
}
