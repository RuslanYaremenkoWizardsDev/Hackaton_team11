package com.example.server.game.repo;

import com.example.server.game.model.UserStatisticModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface USerStatisticRepo extends JpaRepository<UserStatisticModel, Long> {
    UserStatisticModel getUserStatisticModelByIdUser(Long idUser);
}
