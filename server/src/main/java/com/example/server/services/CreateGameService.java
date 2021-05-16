package com.example.server.services;

import com.example.server.exception.InvalidTimeStart;
import com.example.server.tournament.model.TournamentDTO;
import com.example.server.tournament.model.entity.TournamentEntity;
import com.example.server.tournament.model.enums.Level;
import com.example.server.tournament.model.enums.Mode;
import com.example.server.tournament.model.enums.ScenatioOfTournament;
import com.example.server.tournament.model.repo.TournamentRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
@Slf4j
@Component
public class CreateGameService {
    final TournamentRepo tournamentRepo;

    public CreateGameService(TournamentRepo tournamentRepo) {
        this.tournamentRepo = tournamentRepo;
    }

    public void saveGame(TournamentEntity tournamentEntity){
        Mode mode = tournamentEntity.getModeTournament();
        Level level = tournamentEntity.getLevel();
        int numberOfPlayer = tournamentEntity.getNumberOfPlayer();
        ScenatioOfTournament scenatioOfTournament = tournamentEntity.getScenatioOfTournament();

        TournamentDTO tournamentDTO = new TournamentDTO(tournamentEntity.getName(), tournamentEntity.getTournamentDescription(), tournamentEntity.getPlace()
                , tournamentEntity.getDateStartTournament(), tournamentEntity.getDateLastRegistrationOnTournament());
        if (mode != null) {
            tournamentDTO.setModeTournament(mode);
        }
        if (level != null) {
            tournamentDTO.setLevel(level);
        }
        if (numberOfPlayer >= 32) {
            tournamentDTO.setNumberOfPlayer(numberOfPlayer);
        }
        if (scenatioOfTournament != null) {
            tournamentDTO.setScenatioOfTournament(scenatioOfTournament);
        }
        Date date = new Date();
        log.info(String.valueOf(date.getTime()));
        if (date.getTime() > tournamentDTO.getDateStartTournament()) {
            throw new InvalidTimeStart("Start time < current time");
        }
        tournamentRepo.save(tournamentDTO);
    }
}
