package com.example.server.usercredentials.exception.conrolleradvice;

import com.example.server.usercredentials.controllers.RegistrationController;
import com.example.server.usercredentials.exception.InvalidFieldException;
import com.example.server.usercredentials.exception.UserAlreadyExist;
import com.example.server.usercredentials.services.impl.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice(basePackageClasses = {RegistrationController.class, RegistrationService.class})
public class UserCredentialsControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidFieldException.class)
    public ResponseEntity<String> emptyFieldException(Exception e) {
        log.info(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getMessage());
    }

    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<String> userExistException(Exception e) {
        log.info(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getMessage());
    }

//    @ExceptionHandler(PasswordMismatchException.class)
//    public ResponseEntity<String> passwordMismatchException(Exception e) {
//        log.info(e.getMessage());
//        return ResponseEntity
//                .status(HttpStatus.UNAUTHORIZED)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(e.getMessage());
//    }
//
//    @ExceptionHandler(UserNotExistException.class)
//    public ResponseEntity<String> userNotExistException(Exception e) {
//        log.info(e.getMessage());
//        return ResponseEntity
//                .status(HttpStatus.UNAUTHORIZED)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(e.getMessage());
//    }
}
