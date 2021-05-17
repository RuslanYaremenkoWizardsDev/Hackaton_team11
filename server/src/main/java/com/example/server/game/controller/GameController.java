package com.example.server.game.controller;

import com.example.server.game.GameStarter;
import com.example.server.game.repo.BattleUserRepo;
import com.example.server.game.repo.ResultGameRepo;
import com.example.server.game.repo.UserStatisticRepo;
import com.example.server.tournament.repo.TournamentRepo;
import com.example.server.tournament.repo.UserInTournamentRepo;
import com.example.server.usercredentials.repo.UserRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static com.example.server.usercredentials.utils.constants.Mappings.START_GAME;

@RestController
@CrossOrigin("*")
public class GameController {
    private final UserInTournamentRepo userInTournamentRepo;
    private final TournamentRepo tournamentRepo;
    private final ResultGameRepo resultGameRepo;
    private final UserRepository userRepository;
    private final BattleUserRepo battleUserRepo;
    private final UserStatisticRepo userStatisticRepo;

    public GameController(UserInTournamentRepo userInTournamentRepo, TournamentRepo tournamentRepo, ResultGameRepo resultGameRepo, UserRepository userRepository, BattleUserRepo battleUserRepo, UserStatisticRepo userStatisticRepo) {
        this.userInTournamentRepo = userInTournamentRepo;
        this.tournamentRepo = tournamentRepo;
        this.resultGameRepo = resultGameRepo;
        this.userRepository = userRepository;
        this.battleUserRepo = battleUserRepo;
        this.userStatisticRepo = userStatisticRepo;
    }

    @PostMapping(START_GAME)
    public void gameStart(@RequestBody Long id) {
        GameStarter gameStarter = new GameStarter(userInTournamentRepo, tournamentRepo, resultGameRepo, userRepository, battleUserRepo, userStatisticRepo);
        gameStarter.start(tournamentRepo.findById(id).get());
    }

}
