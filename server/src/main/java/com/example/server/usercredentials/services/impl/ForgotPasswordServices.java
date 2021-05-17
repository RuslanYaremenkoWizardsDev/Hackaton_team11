package com.example.server.usercredentials.services.impl;

import com.example.server.usercredentials.model.dto.ForgotPassDto;
import com.example.server.usercredentials.model.entity.Person;
import com.example.server.usercredentials.repo.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import static com.example.server.game.util.Constants.USER_NOT_FOUND;

@Component
public class ForgotPasswordServices {
    private final UserRepository userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ForgotPasswordServices(UserRepository userRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void updatePassword(ForgotPassDto forgotPassDto) {
        Person findPerson = userRepo.findByLogin(forgotPassDto.getLogin());
        if (findPerson != null && bCryptPasswordEncoder.matches(forgotPassDto.getSecretKey(), findPerson.getSecretKey())) {
            findPerson.setPassword(bCryptPasswordEncoder.encode(forgotPassDto.getNewPassword()));
            userRepo.save(findPerson);
            return;
        }
        throw new UsernameNotFoundException(USER_NOT_FOUND);

    }

}
