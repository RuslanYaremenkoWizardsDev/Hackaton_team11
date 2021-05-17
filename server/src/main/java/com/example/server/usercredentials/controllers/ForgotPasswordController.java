package com.example.server.usercredentials.controllers;

import com.example.server.usercredentials.exception.InvalidFieldException;
import com.example.server.usercredentials.model.dto.ForgotPassDto;
import com.example.server.usercredentials.services.impl.ForgotPasswordServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import static com.example.server.usercredentials.utils.constants.ExceptionsMessages.EMPTY_FIELD;
import static com.example.server.usercredentials.utils.constants.Mappings.FORGOT_PASSWORD_MAPPING;
import static com.example.server.usercredentials.utils.constants.Responses.*;

@RestController
@Slf4j
@CrossOrigin("*")
public class ForgotPasswordController {

    private final ForgotPasswordServices forgotPasswordServices;

    public ForgotPasswordController(ForgotPasswordServices forgotPasswordServices) {
        this.forgotPasswordServices = forgotPasswordServices;
    }

    @PostMapping(FORGOT_PASSWORD_MAPPING)
    public ResponseEntity<String> updatePassUser(@Valid @RequestBody ForgotPassDto forgotPassDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidFieldException(EMPTY_FIELD);
        }
        forgotPasswordServices.updatePassword(forgotPassDto);
        log.info(forgotPassDto.getLogin() + UPDATED_PASSWORD);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(SUCCESS);
    }
}
