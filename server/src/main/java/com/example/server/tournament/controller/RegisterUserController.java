package com.example.server.tournament.controller;

import com.example.server.tournament.pool.Pool;
import com.example.server.tournament.services.AddUserToTournament;
import com.example.server.tournament.model.dto.UserDtoForTournament;
import com.example.server.usercredentials.exception.InvalidFieldException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;
import static com.example.server.usercredentials.utils.constants.Mappings.*;

@Slf4j
@RestController
@CrossOrigin("*")
public class RegisterUserController {
    private final AddUserToTournament addUserToTournament;
    private final Pool pool;

    public RegisterUserController(AddUserToTournament addUserToTournament, Pool pool) {
        this.addUserToTournament = addUserToTournament;
        this.pool = pool;
    }

    @PostMapping(REGISTER_USER_IN_TOURNAMENT)
    public void registerUser(@RequestBody UserDtoForTournament userDtoForTournament) {
        addUserToTournament.addUserToTournament(userDtoForTournament.login, userDtoForTournament.getNameTournament());
    }

    @PostMapping(ADD_INVITE)
    public void confirmInvite(@Valid @RequestBody UserDtoForTournament userDtoForTournament, BindingResult bindingResult) throws InterruptedException {
        if (bindingResult.hasErrors()) {
            throw new InvalidFieldException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        pool.addInvite(userDtoForTournament);
    }

    @PostMapping(GET_INVITE)
    public UserDtoForTournament getInvite() {
        return pool.getInvite();
    }

    @PostMapping(GET_ALL_INVITE)
    public List<UserDtoForTournament> getAllInvites() {
        return pool.getAllInvites();
    }
}
