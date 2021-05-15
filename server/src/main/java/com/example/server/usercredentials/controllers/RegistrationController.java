package com.example.server.usercredentials.controllers;

import com.example.server.usercredentials.exception.InvalidFieldException;
import com.example.server.usercredentials.model.dto.UserCredentials;
import com.example.server.usercredentials.model.entity.Person;
import com.example.server.usercredentials.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RegistrationController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/registration")
    public void registredUser(@Valid UserCredentials userCredentials, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidFieldException("Empty field");
        }
    }
}
