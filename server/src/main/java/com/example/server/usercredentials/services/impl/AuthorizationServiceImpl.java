package com.example.server.usercredentials.services.impl;

import com.example.server.usercredentials.model.dto.AuthorizationDto;
import com.example.server.usercredentials.repo.UserRepository;
import com.example.server.usercredentials.services.IAuthorizationService;
import com.example.server.usercredentials.utils.ExceptionsMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public void authorizeUser(AuthorizationDto authorizationDto) {
        String password = getPasswordByLogin(authorizationDto.getLogin());
    }

    private String getPasswordByLogin(String login) {
        String password = userRepository.findByLogin(login).getPassword();
        if (password == null){
            throw new UsernameNotFoundException(String.format(ExceptionsMessages.USER_NOT_FOUND, login));
        }
        return password;
    }
}
