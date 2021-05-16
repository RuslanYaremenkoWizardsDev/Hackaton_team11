package com.example.server.tournament.controller;

import com.example.server.tournament.model.entity.TournamentEntity;
import com.example.server.tournament.services.CreateGameService;
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

    public CreateGameController(CreateGameService createGameService) {
        this.createGameService = createGameService;
    }

    @PostMapping("/game")
    public void saveGame(@Valid @RequestBody TournamentEntity tournamentEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidFieldException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        log.info(tournamentEntity.getName() + " was registered");
        createGameService.saveGame(tournamentEntity);
    }

}
