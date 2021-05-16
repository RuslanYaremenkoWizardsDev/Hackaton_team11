package com.example.server.controller;

import com.example.server.services.AddUserToTournament;
import com.example.server.services.CreateGameService;
import com.example.server.tournament.model.entity.TournamentEntity;
import com.example.server.tournament.model.entity.UserEntityForTournament;
import com.example.server.usercredentials.exception.InvalidFieldException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class CreateGameController {
    private final CreateGameService createGameService;
    private final AddUserToTournament addUserToTournament;

    public CreateGameController(CreateGameService createGameService, AddUserToTournament addUserToTournament) {
        this.createGameService = createGameService;
        this.addUserToTournament = addUserToTournament;
    }

    @PostMapping("/game")
    public void saveGame(@Valid @RequestBody TournamentEntity tournamentEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidFieldException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        createGameService.saveGame(tournamentEntity);
    }

    @PostMapping("/user")
    public void registerUser(@RequestBody UserEntityForTournament userEntityForTournament) {
        addUserToTournament.addUserToTournament(userEntityForTournament.login, userEntityForTournament.getNameTournament());
    }

}
