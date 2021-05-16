package com.example.server.game.controller;

import com.example.server.game.model.BattleUsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BattleUserRepo extends JpaRepository<BattleUsersModel,Long> {

}
