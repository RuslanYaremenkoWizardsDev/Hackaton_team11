package com.example.server.tournament.model.repo;

import com.example.server.tournament.model.UserInTournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInTournamentRepo extends JpaRepository<UserInTournament,Long> {
   List<Long> findByIdTournament(long idTournament);
}
