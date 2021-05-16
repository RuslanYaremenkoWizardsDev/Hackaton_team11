package com.example.server.tournament.services;

import com.example.server.tournament.model.entity.TournamentEntity;
import com.example.server.tournament.model.enums.Status;
import com.example.server.tournament.repo.TournamentRepo;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;
import java.util.List;
@Slf4j
public class CheckStartTimeTournament implements Runnable {
    private final TournamentRepo tournamentRepo;

    public CheckStartTimeTournament(TournamentRepo tournamentRepo) {
        this.tournamentRepo = tournamentRepo;
    }

    @Override
    public void run() {
        Date date = new Date();
        List<TournamentEntity> tournamentDTOList = tournamentRepo.findAllByStatus(Status.IN_PROGRESS);
        tournamentDTOList.forEach(tournamentDTO -> {
            if (date.getTime() > tournamentDTO.getDateStartTournament()) {
                tournamentDTO.setStatus(Status.ACTIVE);
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.info(e.getMessage());
        }
        run();
    }
}
