package com.example.server.game;

import com.example.server.game.repo.BattleUserRepo;
import com.example.server.game.exception.InsufficientNumberOfUsersException;
import com.example.server.game.model.BattleUsersModel;
import com.example.server.game.model.GameModel;
import com.example.server.game.model.UserStatisticModel;
import com.example.server.game.repo.ResultGameRepo;
import com.example.server.game.repo.USerStatisticRepo;
import com.example.server.tournament.model.entity.TournamentEntity;
import com.example.server.tournament.model.entity.UserInTournament;
import com.example.server.tournament.model.enums.Status;
import com.example.server.tournament.repo.TournamentRepo;
import com.example.server.tournament.repo.UserInTournamentRepo;
import com.example.server.usercredentials.exception.InvalidFieldException;
import com.example.server.usercredentials.model.entity.Person;
import com.example.server.usercredentials.repo.UserRepository;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class GameService {
    private final UserInTournamentRepo userInTournamentRepo;
    private final TournamentRepo tournamentRepo;
    private final ResultGameRepo resultGameRepo;
    private final UserRepository userRepository;
    private final BattleUserRepo battleUserRepo;
    private final USerStatisticRepo userStatisticRepo;


    public GameService(UserInTournamentRepo userInTournamentRepo, TournamentRepo tournamentRepo, ResultGameRepo resultGameRepo, UserRepository userRepository, BattleUserRepo battleUserRepo, USerStatisticRepo userStatisticRepo) {
        this.userInTournamentRepo = userInTournamentRepo;
        this.tournamentRepo = tournamentRepo;
        this.resultGameRepo = resultGameRepo;

        this.userRepository = userRepository;
        this.battleUserRepo = battleUserRepo;
        this.userStatisticRepo = userStatisticRepo;
    }

    public void play(TournamentEntity tournamentEntity) {
        Optional<TournamentEntity> optionalTournamentEntity = tournamentRepo.findByName(tournamentEntity.getName());
        if (optionalTournamentEntity.isEmpty()) {
            throw new InvalidFieldException("Problem with tournament");
        }
        List<UserInTournament> optionalUserInTournaments = userInTournamentRepo.findByIdTournament(optionalTournamentEntity.get().getId());
        if (optionalUserInTournaments.size() % 2 != 0 || optionalUserInTournaments.size() < 4) {
            throw new InsufficientNumberOfUsersException("not enough users");
        }
        UserInTournament winner = getWinner(optionalUserInTournaments, tournamentEntity);
        Date date = new Date();
        GameModel gameModel = new GameModel(null, optionalTournamentEntity.get().getId(), date.getTime(), winner.getIdUser());
        optionalTournamentEntity.get().setStatus(Status.FINISHED);
        resultGameRepo.save(gameModel);
    }

    public UserInTournament getWinner(List<UserInTournament> allUserInTournament, TournamentEntity tournamentEntity) {
        int size = allUserInTournament.size();
        List<UserInTournament> afterRound = new ArrayList<>(size / 2);
        List<UserInTournament> userInTournaments1 = new ArrayList<>(size / 2);
        List<UserInTournament> userInTournaments2 = new ArrayList<>(size / 2);
        for (int i = 0; i < size / 2; i++) {
            userInTournaments1.add(allUserInTournament.get(i));
        }
        for (int i = size / 2; i < allUserInTournament.size(); i++) {
            userInTournaments2.add(allUserInTournament.get(i));
        }
        for (int i = 0; i < userInTournaments1.size(); i++) {
            for (int j = userInTournaments2.size() - 1; j >= 0; j--) {
                Person person = userRepository.findById(userInTournaments1.get(i).getIdUser()).get();
                Person person2 = userRepository.findById(userInTournaments2.get(j).getIdUser()).get();
                String winner = getWinnerByPower(person, person2);
                BattleUsersModel battleUsersModel = new BattleUsersModel(null, tournamentEntity.getId(), person.getLogin(), person2.getLogin(), winner);
                battleUserRepo.save(battleUsersModel);
                afterRound.add(userInTournaments1.get(i));
                UserStatisticModel winerStatic =
                        userStatisticRepo.getUserStatisticModelByIdUser(userInTournaments1.get(i).getIdUser());
                UserStatisticModel loseStatic =
                        userStatisticRepo.getUserStatisticModelByIdUser(userInTournaments2.get(j).getIdUser());
                loseStatic.setLose(loseStatic.getWins() + 1);
                i++;
            }
        }
        if (afterRound.size() != 2) {
            getWinner(afterRound, tournamentEntity);
        }
        Person person = userRepository.findById(afterRound.get(0).getIdUser()).get();
        Person person2 = userRepository.findById(afterRound.get(1).getIdUser()).get();
        UserStatisticModel winerStatic =
                userStatisticRepo.getUserStatisticModelByIdUser(person.getId());
        winerStatic.setWins(winerStatic.getWins() + 1);
        winerStatic.setWinsCup(winerStatic.getWinsCup() + 1);
        UserStatisticModel loseStatic =
                userStatisticRepo.getUserStatisticModelByIdUser(person2.getId());
        loseStatic.setLose(loseStatic.getLose() + 1);
        BattleUsersModel battleUsersModel = new BattleUsersModel(null, tournamentEntity.getId(), person.getLogin(), person2.getLogin(), person.getLogin());
        battleUserRepo.save(battleUsersModel);
        return afterRound.get(0);
    }

    public String getWinnerByPower(Person person, Person person2) {
        if (person.getPower().equals(person2.getPower())) {
            return "DRAW";
        }
        return person.getPower() > person2.getPower() ? person.getLogin() : person2.getLogin();

    }

}
