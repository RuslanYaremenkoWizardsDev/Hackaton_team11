package com.example.server.tournament.services;

import com.example.server.tournament.exception.FullTournamentException;
import com.example.server.tournament.exception.InvalidTimeStart;
import com.example.server.tournament.exception.TournamentNotFoundException;
import com.example.server.tournament.model.entity.TournamentEntity;
import com.example.server.tournament.model.entity.UserInTournament;
import com.example.server.tournament.model.enums.Status;
import com.example.server.tournament.repo.TournamentRepo;
import com.example.server.tournament.repo.UserInTournamentRepo;
import com.example.server.usercredentials.exception.UserNotFoundException;
import com.example.server.usercredentials.model.entity.Person;
import com.example.server.usercredentials.repo.UserRepository;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class AddUserToTournament {
    final UserInTournamentRepo userInTournamentRepo;
    final UserRepository userRepository;
    final TournamentRepo tournamentRepo;

    public AddUserToTournament(UserInTournamentRepo userInTournamentRepo, UserRepository userRepository, TournamentRepo tournamentRepo) {
        this.userInTournamentRepo = userInTournamentRepo;
        this.userRepository = userRepository;
        this.tournamentRepo = tournamentRepo;


    }

    public void addUserToTournament(String login, String nameTournament) {
        Optional<TournamentEntity> optionalTournamentDTO = tournamentRepo.findByName(nameTournament);
        if (optionalTournamentDTO.isEmpty()) {
            throw new TournamentNotFoundException("Tournament with " + nameTournament + " not found");
        }
        Person person = userRepository.findByLogin(login);
        if (person == null) {
            throw new UserNotFoundException(String.format("User with login %s not found", login));
        }
        UserInTournament userInTournament = new UserInTournament(optionalTournamentDTO.get().getId(), person.getId());
        Date date = new Date();
        if (optionalTournamentDTO.get().getDateLastRegistrationOnTournament() <= date.getTime()) {
            throw new InvalidTimeStart("Time for registration on " + nameTournament + " end");
        }
        List<UserInTournament> listRegisteredUserOnThisTournament = userInTournamentRepo.findByIdTournament(optionalTournamentDTO.get().getId());
        if (listRegisteredUserOnThisTournament.size() >= optionalTournamentDTO.get().getNumberOfPlayer()
                && optionalTournamentDTO.get().getStatus() == Status.IN_PROGRESS) {
            throw new FullTournamentException("Tournament " + nameTournament + " have not free space");
        }
        userInTournamentRepo.save(userInTournament);

    }
}
