package com.example.server.game;

import com.example.server.game.repo.BattleUserRepo;
import com.example.server.game.repo.ResultGameRepo;
import com.example.server.game.repo.USerStatisticRepo;
import com.example.server.tournament.model.entity.TournamentEntity;
import com.example.server.tournament.repo.TournamentRepo;
import com.example.server.tournament.repo.UserInTournamentRepo;
import com.example.server.usercredentials.repo.UserRepository;

public class GameStarter implements Runnable {
    private final UserInTournamentRepo userInTournamentRepo;
    private final TournamentRepo tournamentRepo;
    private final ResultGameRepo resultGameRepo;
    private final UserRepository userRepository;
    private final BattleUserRepo battleUserRepo;
    private final USerStatisticRepo uSerStatisticRepo;

    public GameStarter(UserInTournamentRepo userInTournamentRepo, TournamentRepo tournamentRepo, ResultGameRepo resultGameRepo, UserRepository userRepository, BattleUserRepo battleUserRepo, USerStatisticRepo uSerStatisticRepo) {
        this.userInTournamentRepo = userInTournamentRepo;
        this.tournamentRepo = tournamentRepo;
        this.resultGameRepo = resultGameRepo;
        this.userRepository = userRepository;
        this.battleUserRepo = battleUserRepo;
        this.uSerStatisticRepo = uSerStatisticRepo;
    }

    @Override
    public void run() {

    }

    public void start(TournamentEntity tournamentEntity) {
        run();
        GameService gameService = new GameService(userInTournamentRepo, tournamentRepo, resultGameRepo, userRepository, battleUserRepo, uSerStatisticRepo);
        gameService.play(tournamentEntity);
    }
}
