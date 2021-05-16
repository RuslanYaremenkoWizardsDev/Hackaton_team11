package com.example.server.game.controller;

import com.example.server.game.GameStarter;
import com.example.server.game.repo.ResultGameRepo;
import com.example.server.game.repo.USerStatisticRepo;
import com.example.server.tournament.repo.TournamentRepo;
import com.example.server.tournament.repo.UserInTournamentRepo;
import com.example.server.usercredentials.repo.UserRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class Controller {
    private final UserInTournamentRepo userInTournamentRepo;
    private final TournamentRepo tournamentRepo;
    private final ResultGameRepo resultGameRepo;
    private final UserRepository userRepository;
    private final BattleUserRepo battleUserRepo;
    private final USerStatisticRepo uSerStatisticRepo;

    public Controller(UserInTournamentRepo userInTournamentRepo, TournamentRepo tournamentRepo, ResultGameRepo resultGameRepo, UserRepository userRepository, BattleUserRepo battleUserRepo, USerStatisticRepo uSerStatisticRepo) {
        this.userInTournamentRepo = userInTournamentRepo;
        this.tournamentRepo = tournamentRepo;
        this.resultGameRepo = resultGameRepo;
        this.userRepository = userRepository;
        this.battleUserRepo = battleUserRepo;
        this.uSerStatisticRepo = uSerStatisticRepo;
    }
    @PostMapping("/gamestart")
    public void gameStart(@RequestBody Long id){
        GameStarter gameStarter = new GameStarter(userInTournamentRepo,tournamentRepo,resultGameRepo, userRepository, battleUserRepo, uSerStatisticRepo);
        gameStarter.start(tournamentRepo.findById(id).get());

    }

}
