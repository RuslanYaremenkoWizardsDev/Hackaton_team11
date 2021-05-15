package com.example.server.usercredentials.services;

import com.example.server.usercredentials.exception.UserAlreadyExist;
import com.example.server.usercredentials.model.dto.UserCredentials;
import com.example.server.usercredentials.model.entity.Person;
import com.example.server.usercredentials.repo.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class RegistrationService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public RegistrationService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void saveUserToDb(UserCredentials userCredentials) {
        Person person = new Person(userCredentials.getLogin(),userCredentials.getEmail(),
                bCryptPasswordEncoder.encode(userCredentials.getPassword()), bCryptPasswordEncoder.encode(userCredentials.getSecretKey()));
        userRepository.save(person);

    }

    private void checkLoginExist(String login, String email) {
        if (userRepository.findByLogin(login) != null || userRepository.findByEmail(email) != null) {
            throw new UserAlreadyExist(login == null ? email :login + " user with same credentials exist");
        }
    }
}
