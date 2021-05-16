package com.example.server.services;

import com.example.server.exception.FullTournamentException;
import com.example.server.exception.InvalidTimeStart;
import com.example.server.exception.TournamentNotFoundException;
import com.example.server.tournament.model.TournamentDTO;
import com.example.server.tournament.model.UserInTournament;
import com.example.server.tournament.model.repo.TournamentRepo;
import com.example.server.tournament.model.repo.UserInTournamentRepo;
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
        Optional<TournamentDTO> optionalTournamentDTO = tournamentRepo.findByName(nameTournament);
        if (optionalTournamentDTO.isEmpty()) {
            throw new TournamentNotFoundException("Tournament with " + nameTournament + " not found");
        }
        UserInTournament userInTournament = new UserInTournament(optionalTournamentDTO.get().getId(), userRepository.findByLogin(login).getId());
        Date date = new Date();
        if (optionalTournamentDTO.get().getDateLastRegistrationOnTournament() <= date.getTime()) {
            throw new InvalidTimeStart("Time for registration on " + nameTournament + " end");
        }
        List<Long> listRegisteredUserOnThisTournament = userInTournamentRepo.findByIdTournament(optionalTournamentDTO.get().getId());
        if (listRegisteredUserOnThisTournament.size() >= optionalTournamentDTO.get().getNumberOfPlayer()) {
            throw new FullTournamentException("Tournament " + nameTournament + " have not free space");
        }
        userInTournamentRepo.save(userInTournament);

    }
}
