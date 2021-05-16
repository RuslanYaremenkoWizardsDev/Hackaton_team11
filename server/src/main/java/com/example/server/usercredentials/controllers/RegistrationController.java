package com.example.server.usercredentials.controllers;

import com.example.server.usercredentials.exception.InvalidFieldException;
import com.example.server.usercredentials.model.dto.UserCredentials;
import com.example.server.usercredentials.repo.UserRepository;
import com.example.server.usercredentials.services.impl.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import static com.example.server.usercredentials.utils.constants.Responses.SUCCESS;
import static com.example.server.usercredentials.utils.constants.Responses.WAS_REGISTERED;
import static com.example.server.usercredentials.utils.constants.ExceptionsMessages.EMPTY_FIELD;
import static com.example.server.usercredentials.utils.constants.Mappings.REGISTRATION_MAPPING;

@Slf4j
@RestController
@CrossOrigin("*")
public class RegistrationController {
    private final UserRepository userRepository;
    private final RegistrationService registrationService;

    public RegistrationController(UserRepository userRepository, RegistrationService registrationService) {
        this.userRepository = userRepository;
        this.registrationService = registrationService;
    }

    @PostMapping(REGISTRATION_MAPPING)
    public ResponseEntity<String> registryUser(@Valid @RequestBody UserCredentials userCredentials, BindingResult bindingResult) {
        log.info(userCredentials.getLogin());
        if (bindingResult.hasErrors()) {
            throw new InvalidFieldException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        registrationService.saveUserToDb(userCredentials);
        log.info(userCredentials.getLogin() + WAS_REGISTERED);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(SUCCESS);
    }
}
