package com.example.server.tournament.controller;

import com.example.server.tournament.model.dto.TournamentDto;
import com.example.server.tournament.model.entity.TournamentEntity;
import com.example.server.tournament.services.CheckStartTimeTournament;
import com.example.server.tournament.services.CreateGameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class TournamentController {
    private final CreateGameService createGameService;
    private final CheckStartTimeTournament startTimeTournament;
    public TournamentController(CreateGameService createGameService, CheckStartTimeTournament startTimeTournament) {
        this.createGameService = createGameService;
        this.startTimeTournament = startTimeTournament;
    }

    @PostMapping("/addTournament")
    public List<TournamentEntity> saveGame(@Valid @Payload TournamentDto tournamentDto) {
        System.out.println(tournamentDto.toString());

        TournamentEntity tournamentEntity = new TournamentEntity(
                null,
                tournamentDto.getStatus(),
                tournamentDto.getName(),
                tournamentDto.getTournamentDescription(),
                tournamentDto.getModeTournament(),
                tournamentDto.getPlace(),
                tournamentDto.getDateStartTournament(),
                tournamentDto.getDateLastRegistrationOnTournament(),
                tournamentDto.getLevel(),
                tournamentDto.getNumberOfPlayer(),
                tournamentDto.getScenarioOfTournament()
        );

        createGameService.saveGame(tournamentEntity);
        log.info(tournamentEntity.getName() + " was registered");
        return createGameService.getAllTournament();
    }

    @PostMapping("/getTournament")
    public List<TournamentEntity> getAllTournaments() {
        startTimeTournament.run();
        return createGameService.getAllTournament();
    }


}
