package com.example.server.game.repo;

import com.example.server.game.model.GameModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultGameRepo extends JpaRepository<GameModel, Long> {

}
