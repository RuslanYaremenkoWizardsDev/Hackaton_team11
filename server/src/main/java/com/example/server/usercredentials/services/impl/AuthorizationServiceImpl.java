package com.example.server.usercredentials.services.impl;

import com.example.server.usercredentials.exception.IncorrectPasswordException;
import com.example.server.usercredentials.exception.UserNotFoundException;
import com.example.server.usercredentials.model.dto.AuthorizationDto;
import com.example.server.usercredentials.model.entity.Person;
import com.example.server.usercredentials.repo.UserRepository;
import com.example.server.usercredentials.services.IAuthorizationService;
import com.example.server.usercredentials.utils.constants.ExceptionsMessages;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements IAuthorizationService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthorizationServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Person authorizeUser(AuthorizationDto authorizationDto) {
        Person person;
        if (authorizationDto.getLogin().contains("@")) {
            person = userRepository.findByEmail(authorizationDto.getLogin());
        } else {
            person = userRepository.findByLogin(authorizationDto.getLogin());
        }
        if (person == null) {
            throw new UserNotFoundException(String.format(ExceptionsMessages.USER_NOT_FOUND, authorizationDto.getLogin()));
        }
        if (!bCryptPasswordEncoder.matches(authorizationDto.getPassword(), person.getPassword())) {
            throw new IncorrectPasswordException(ExceptionsMessages.INCORRECT_PASSWORD);
        }
        return person;
    }
}
