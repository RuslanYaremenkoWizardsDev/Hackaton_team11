package com.example.server.usercredentials.services;

import com.example.server.usercredentials.model.entity.Person;
import com.example.server.usercredentials.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ForgotPasswordServices {
    @Autowired
    private UserRepository userRepo;

    public void updatePassword ( String login, String secretKey, String newPassword)  {
        Person findPerson = userRepo.findBySecretKey(login);
        if (findPerson != null && findPerson.getSecretKey().equals(secretKey)) {
            findPerson.setPassword(newPassword);
            userRepo.save(findPerson);
        } else {
           // throw new MyExc;
        }
    }

}
