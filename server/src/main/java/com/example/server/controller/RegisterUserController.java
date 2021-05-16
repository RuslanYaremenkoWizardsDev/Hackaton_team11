package com.example.server.controller;

import com.example.server.services.AddUserToTournament;
import com.example.server.tournament.model.entity.UserEntityForTournament;
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
    public void registerUser(@RequestBody UserEntityForTournament userEntityForTournament) {
        addUserToTournament.addUserToTournament(userEntityForTournament.login, userEntityForTournament.getNameTournament());
    }
}
