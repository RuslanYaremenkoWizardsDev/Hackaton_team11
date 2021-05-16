package com.example.server.game.controller;

import com.example.server.game.model.UserStatisticModel;
import com.example.server.game.repo.UserStatisticRepo;
import com.example.server.usercredentials.exception.UserNotFoundException;
import com.example.server.usercredentials.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Slf4j
@RestController
public class UserStatisticController {
    private final UserStatisticRepo userStatisticRepo;
    private final UserRepository userRepository;

    public UserStatisticController(UserStatisticRepo userStatisticRepo, UserRepository userRepository) {
        this.userStatisticRepo = userStatisticRepo;
        this.userRepository = userRepository;
    }

    @PostMapping("/getStaticByLogin")
    public UserStatisticModel userStatisticModels(@RequestBody String login) {
        Long id = userRepository.findByLogin(login).getId();
        if (id == null) {
            throw new UserNotFoundException("User not found " + login);
        }
        return userStatisticRepo.getUserStatisticModelByIdUser(id);
    }

    @PostMapping("/getStaticForAllUser")
    public List<UserStatisticModel> allStatisticModels() {
        List<UserStatisticModel> allStats = userStatisticRepo.findAll();
        if (allStats.size() < 1) {
            throw new UserNotFoundException("Users not found ");
        }
        return allStats;
    }
}
