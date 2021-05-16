package com.example.server.tournament.controller;

import com.example.server.tournament.model.dto.TournamentDto;
import com.example.server.tournament.model.entity.TournamentEntity;
import com.example.server.tournament.services.CreateGameService;
import com.example.server.usercredentials.exception.InvalidFieldException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin("*")
public class CreateGameController {
    private final CreateGameService createGameService;

    public CreateGameController(CreateGameService createGameService) {
        this.createGameService = createGameService;
    }

    @PostMapping("/game")
    public List<TournamentEntity> saveGame(@Valid @RequestBody TournamentDto tournamentDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidFieldException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        TournamentEntity tournamentEntity = TournamentEntity
                .builder()
                .status(tournamentDto.getStatus())
                .name(tournamentDto.getName())
                .tournamentDescription(tournamentDto.getTournamentDescription())
                .modeTournament(tournamentDto.getModeTournament())
                .place(tournamentDto.getPlace())
                .dateStartTournament(tournamentDto.getDateStartTournament())
                .dateLastRegistrationOnTournament(tournamentDto.getDateLastRegistrationOnTournament())
                .level(tournamentDto.getLevel())
                .numberOfPlayer(tournamentDto.getNumberOfPlayer())
                .scenatioOfTournament(tournamentDto.getScenarioOfTournament())
                .build();
        createGameService.saveGame(tournamentEntity);
        log.info(tournamentEntity.getName() + " was registered");
        return createGameService.getAllTournament();
    }

}
