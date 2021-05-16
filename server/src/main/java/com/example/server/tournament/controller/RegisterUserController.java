package com.example.server.tournament.controller;

import com.example.server.tournament.services.AddUserToTournament;
import com.example.server.tournament.model.dto.UserDtoForTournament;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RegisterUserController {
    private final AddUserToTournament addUserToTournament;

    public RegisterUserController(AddUserToTournament addUserToTournament) {
        this.addUserToTournament = addUserToTournament;
    }

    @PostMapping("/user")
    public void registerUser(@RequestBody UserDtoForTournament userDtoForTournament) {
        addUserToTournament.addUserToTournament(userDtoForTournament.login, userDtoForTournament.getNameTournament());
    }
}
