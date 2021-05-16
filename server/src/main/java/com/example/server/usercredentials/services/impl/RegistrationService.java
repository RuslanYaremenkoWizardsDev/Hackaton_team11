package com.example.server.usercredentials.services.impl;

import com.example.server.game.model.UserStatisticModel;
import com.example.server.game.repo.USerStatisticRepo;
import com.example.server.usercredentials.exception.UserAlreadyExist;
import com.example.server.usercredentials.model.dto.UserCredentials;
import com.example.server.usercredentials.model.entity.Person;
import com.example.server.usercredentials.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import static com.example.server.usercredentials.utils.constants.ExceptionsMessages.USER_WITH_SAME_CREDENTIALS_EXIST;

@Slf4j
@Component
public class RegistrationService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserStatisticRepo userStatisticRepo;

    public RegistrationService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserStatisticRepo userStatisticRepo) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userStatisticRepo = userStatisticRepo;
    }

    public void saveUserToDb(UserCredentials userCredentials) {
        checkLoginExist(userCredentials.getLogin(), userCredentials.getEmail());
        Person person = new Person(userCredentials.getLogin(), userCredentials.getEmail(),
                bCryptPasswordEncoder.encode(userCredentials.getPassword()), bCryptPasswordEncoder.encode(userCredentials.getSecretKey()));
        userRepository.save(person);
        userStatisticRepo.save(new UserStatisticModel(null, person.getId(), 0L, 0L, 0L, 0L));
    }

    private void checkLoginExist(String login, String email) {
        Person findPerson = userRepository.findByLogin(login);
        if (findPerson != null || userRepository.findByEmail(email) != null) {
            log.info(findPerson != null ? login : email + USER_WITH_SAME_CREDENTIALS_EXIST);
            throw new UserAlreadyExist(findPerson != null ? login + USER_WITH_SAME_CREDENTIALS_EXIST : email + USER_WITH_SAME_CREDENTIALS_EXIST);
        }
    }
}
