package com.example.server.usercredentials.services.impl;

import com.example.server.usercredentials.exception.IncorrectPasswordException;
import com.example.server.usercredentials.model.dto.AuthorizationDto;
import com.example.server.usercredentials.model.entity.Person;
import com.example.server.usercredentials.repo.UserRepository;
import com.example.server.usercredentials.services.IAuthorizationService;
import com.example.server.usercredentials.utils.constants.ExceptionsMessages;
import com.example.server.usercredentials.utils.JwtTokenProvider;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements IAuthorizationService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthorizationServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String authorizeUser(AuthorizationDto authorizationDto) {
        Person person = userRepository.findByLogin(authorizationDto.getLogin());
        if (person == null) {
            throw new UsernameNotFoundException(String.format(ExceptionsMessages.USER_NOT_FOUND, authorizationDto.getLogin()));
        }
        if (!bCryptPasswordEncoder.matches(authorizationDto.getPassword(), person.getPassword())) {
            throw new IncorrectPasswordException(ExceptionsMessages.INCORRECT_PASSWORD);
        }
        return jwtTokenProvider.createToken(person);
    }
}
