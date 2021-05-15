package com.example.server.tournament.model.repo;

import com.example.server.tournament.model.TournamentDTO;
import com.example.server.tournament.model.UserInTournament;
import com.example.server.tournament.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TournamentRepo extends JpaRepository<TournamentDTO,Long> {
    List<TournamentDTO> findAllByStatus(Status status);
}
