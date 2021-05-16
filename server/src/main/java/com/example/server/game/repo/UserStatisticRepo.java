package com.example.server.game.repo;

import com.example.server.game.model.UserStatisticModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserStatisticRepo extends JpaRepository<UserStatisticModel, Long> {
    UserStatisticModel getUserStatisticModelByIdUser(Long idUser);
}
