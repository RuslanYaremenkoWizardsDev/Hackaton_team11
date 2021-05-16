package com.example.server.usercredentials.services;

import com.example.server.usercredentials.model.entity.Person;
import com.example.server.usercredentials.repo.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import static com.example.server.game.util.Constants.USER_NOT_FOUND;

@Component
public class ForgotPasswordServices {
    private final UserRepository userRepo;

    public ForgotPasswordServices(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public void updatePassword(String login, String secretKey, String newPassword) {
        Person findPerson = userRepo.findBySecretKey(login);
        if (findPerson != null && findPerson.getSecretKey().equals(secretKey)) {
            findPerson.setPassword(newPassword);
            userRepo.save(findPerson);
            return;
        }
        throw new UsernameNotFoundException(USER_NOT_FOUND);

    }

}
