package com.example.server.game.controller.controlleradvice;

import com.example.server.game.GameService;
import com.example.server.game.controller.GameController;
import com.example.server.game.controller.UserStatisticController;
import com.example.server.game.exception.InsufficientNumberOfUsersException;
import com.example.server.usercredentials.exception.InvalidFieldException;
import com.example.server.usercredentials.exception.UserNotFoundException;
import com.example.server.usercredentials.services.impl.AuthorizationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(basePackageClasses = {GameController.class, UserStatisticController.class, GameService.class, AuthorizationServiceImpl.class})
public class GameControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFoundException(Exception e) {
        log.info(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getMessage());
    }

    @ExceptionHandler(InvalidFieldException.class)
    public ResponseEntity<String> invalidFieldException(Exception e) {
        log.info(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getMessage());
    }

    @ExceptionHandler(InsufficientNumberOfUsersException.class)
    public ResponseEntity<String> insufficientNumberOfUsersException(Exception e) {
        log.info(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getMessage());
    }
}

