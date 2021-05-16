package com.example.server.tournament.controller;

import com.example.server.tournament.model.dto.TournamentDto;
import com.example.server.tournament.model.entity.TournamentEntity;
import com.example.server.tournament.services.CheckStartTimeTournament;
import com.example.server.tournament.services.CreateTournamentService;
import com.example.server.usercredentials.exception.InvalidFieldException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;
import static com.example.server.usercredentials.utils.constants.Mappings.ADD_TOURNAMENT;
import static com.example.server.usercredentials.utils.constants.Mappings.GET_TOURNAMENT;

@Slf4j
@RestController
@CrossOrigin("*")
public class TournamentController {
    private final CreateTournamentService createTournamentService;
    private final CheckStartTimeTournament startTimeTournament;

    public TournamentController(CreateTournamentService createTournamentService, CheckStartTimeTournament startTimeTournament) {
        this.createTournamentService = createTournamentService;
        this.startTimeTournament = startTimeTournament;
    }

    @PostMapping(ADD_TOURNAMENT)
    public List<TournamentEntity> saveGame(@Valid @RequestBody TournamentDto tournamentDto, BindingResult bindingResult) {
        System.out.println(tournamentDto.toString());
        if (bindingResult.hasErrors()) {
            throw new InvalidFieldException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

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

        createTournamentService.saveGame(tournamentEntity);
        log.info(tournamentEntity.getName() + " was registered");
        return createTournamentService.getAllTournament();
    }

    @PostMapping(GET_TOURNAMENT)
    public List<TournamentEntity> getAllTournaments() {
        startTimeTournament.run();
        return createTournamentService.getAllTournament();
    }

}
