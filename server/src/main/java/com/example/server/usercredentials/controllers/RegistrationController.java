package com.example.server.usercredentials.controllers;

import com.example.server.usercredentials.model.entity.Person;
import com.example.server.usercredentials.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/registration")
    public void registredUser(){
        userRepository.save(new Person("jeiffd","j@","123","jfjidfj"));
    }
}
