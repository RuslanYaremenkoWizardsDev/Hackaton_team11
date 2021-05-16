package com.example.server.tournament.services;

import com.example.server.tournament.exception.InvalidStatusTournamentException;
import com.example.server.tournament.exception.TournamentNotFoundException;
import com.example.server.tournament.model.entity.TournamentEntity;
import com.example.server.tournament.model.enums.Status;
import com.example.server.tournament.repo.TournamentRepo;
import org.springframework.stereotype.Component;
import java.util.Optional;


@Component
public class ChangeStatusTournament {
    private final TournamentRepo tournamentRepo;
    private final String notFound = "Tournament not found";

    public ChangeStatusTournament(TournamentRepo tournamentRepo) {
        this.tournamentRepo = tournamentRepo;
    }

    public void inProgressActive(String tournamentName) {
        Optional<TournamentEntity> optionalTournamentDTO = tournamentRepo.findByName(tournamentName);
        if (optionalTournamentDTO.isEmpty()) {
            throw new TournamentNotFoundException(notFound);
        }
        if (optionalTournamentDTO.get().getStatus() == Status.IN_PROGRESS) {
            optionalTournamentDTO.get().setStatus(Status.ACTIVE);
        } else {
            throw new InvalidStatusTournamentException("This tournament not IN-Progress");
        }
    }

    public void activeToFinished(String tournamentName) {
        Optional<TournamentEntity> optionalTournamentDTO = tournamentRepo.findByName(tournamentName);
        if (optionalTournamentDTO.isEmpty()) {
            throw new TournamentNotFoundException(notFound);
        }
        if (optionalTournamentDTO.get().getStatus() == Status.ACTIVE) {
            optionalTournamentDTO.get().setStatus(Status.FINISHED);
        } else {
            throw new InvalidStatusTournamentException("This tournament not ACTIVE");
        }
    }
}
