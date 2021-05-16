package com.example.server.tournament.repo;

import com.example.server.tournament.model.entity.TournamentEntity;
import com.example.server.tournament.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TournamentRepo extends JpaRepository<TournamentEntity, Long> {
    List<TournamentEntity> findAllByStatus(Status status);

    Optional<TournamentEntity> findByName(String name);

    Optional<TournamentEntity> findById(Long id);
}
