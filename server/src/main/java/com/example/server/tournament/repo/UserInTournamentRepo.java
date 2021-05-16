package com.example.server.tournament.repo;

import com.example.server.tournament.model.entity.UserInTournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInTournamentRepo extends JpaRepository<UserInTournament,Long> {
   List<UserInTournament> findByIdTournament(long idTournament);
   List<Long> findByIdUser(UserInTournament userInTournament);
}
