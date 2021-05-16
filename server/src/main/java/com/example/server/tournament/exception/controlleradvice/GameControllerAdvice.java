package com.example.server.tournament.exception.controlleradvice;

import com.example.server.tournament.controller.CreateGameController;
import com.example.server.tournament.controller.RegisterUserController;
import com.example.server.tournament.exception.FullTournamentException;
import com.example.server.tournament.exception.InvalidTimeStart;
import com.example.server.tournament.exception.TournamentNotFoundException;
import com.example.server.tournament.exception.TournamentWithSameNameExist;
import com.example.server.tournament.services.AddUserToTournament;
import com.example.server.tournament.services.CreateGameService;
import com.example.server.usercredentials.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(basePackageClasses = {CreateGameController.class, RegisterUserController.class, AddUserToTournament.class, CreateGameService.class})
public class GameControllerAdvice {

    @ExceptionHandler(FullTournamentException.class)
    public ResponseEntity<String> emptyFieldException(Exception e) {
        log.info(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getMessage());
    }

    @ExceptionHandler(InvalidTimeStart.class)
    public ResponseEntity<String> invalidTimeStartException(Exception e) {
        log.info(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getMessage());
    }

    @ExceptionHandler(TournamentNotFoundException.class)
    public ResponseEntity<String> tournamentNotFoundException(Exception e) {
        log.info(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFoundException(Exception e) {
        log.info(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getMessage());
    }

    @ExceptionHandler(TournamentWithSameNameExist.class)
    public ResponseEntity<String> tournamentWithSameExistException(Exception e) {
        log.info(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getMessage());
    }
}
