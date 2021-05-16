package com.example.server.tournament.controller;

import com.example.server.tournament.model.dto.TournamentStatisticModel;
import com.example.server.tournament.model.entity.TournamentEntity;
import com.example.server.tournament.model.enums.Status;
import com.example.server.tournament.repo.TournamentRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin("*")
public class TournamentStatisticController {
    private final TournamentRepo tournamentRepo;

    public TournamentStatisticController(TournamentRepo tournamentRepo) {
        this.tournamentRepo = tournamentRepo;
    }

    @PostMapping("/usersTournament")
    public TournamentStatisticModel getTournamentStatistic() {
        List<TournamentEntity> all = tournamentRepo.findAll();
        List<TournamentEntity> active = tournamentRepo.findAllByStatus(Status.ACTIVE);
        List<TournamentEntity> finished = tournamentRepo.findAllByStatus(Status.FINISHED);
        List<TournamentEntity> notStarted = tournamentRepo.findAllByStatus(Status.IN_PROGRESS);
        return new TournamentStatisticModel(all.size(), active.size(), finished.size(), notStarted.size());
    }
}
