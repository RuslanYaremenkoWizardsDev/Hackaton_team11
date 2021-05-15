package com.example.server.usercredentials.controllers;

import com.example.server.usercredentials.exception.InvalidFieldException;
import com.example.server.usercredentials.model.dto.AuthorizationDto;
import com.example.server.usercredentials.services.impl.AuthorizationServiceImpl;
import com.example.server.usercredentials.utils.constants.Responses;
import com.example.server.usercredentials.utils.constants.Mappings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import static com.example.server.usercredentials.utils.constants.Responses.WAS_AUTHORIZE;

@RestController
@Slf4j
public class AuthorizationController {
    private final AuthorizationServiceImpl authorizationService;

    public AuthorizationController(AuthorizationServiceImpl authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping(Mappings.AUTHORIZATION_MAPPING)
    public ResponseEntity<String> authorizeUser(@Valid @RequestBody @NotNull AuthorizationDto authorizationDto, BindingResult bindingResult) {
        log.info(authorizationDto.getLogin());
        if (bindingResult.hasErrors()) {
            throw new InvalidFieldException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        String token = authorizationService.authorizeUser(authorizationDto);
        log.info(authorizationDto.getLogin() + WAS_AUTHORIZE);
        return ResponseEntity.status(HttpStatus.OK).header("Authorization", token).contentType(MediaType.APPLICATION_JSON).body(Responses.SUCCESS);
    }
}
