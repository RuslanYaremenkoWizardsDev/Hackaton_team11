package com.example.server.controller;

import com.example.server.tournament.model.TournamentDTO;
import com.example.server.tournament.model.enums.Level;
import com.example.server.tournament.model.enums.Mode;
import com.example.server.tournament.model.enums.ScenatioOfTournament;
import com.example.server.tournament.model.enums.Status;
import com.example.server.tournament.model.repo.TournamentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateGameController {
    @Autowired
    TournamentRepo tournamentRepo;
    @PostMapping("/game")
    public void save(){
        tournamentRepo.save(new TournamentDTO(null, Status.IN_PROGRESS,"1","fj", Mode.CUP,"khr",123123123,34234234, Level.MIDDLE,32, ScenatioOfTournament.ONE_MATCH));
    }

}
