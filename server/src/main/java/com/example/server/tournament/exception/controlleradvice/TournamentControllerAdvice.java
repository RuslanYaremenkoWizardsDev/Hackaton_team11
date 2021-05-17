package com.example.server.tournament.exception.controlleradvice;

import com.example.server.game.exception.UserWasRegisterInThisTournament;
import com.example.server.tournament.controller.TournamentController;
import com.example.server.tournament.controller.RegisterUserController;
import com.example.server.tournament.exception.*;
import com.example.server.tournament.pool.Pool;
import com.example.server.tournament.services.AddUserToTournament;
import com.example.server.tournament.services.ChangeStatusTournament;
import com.example.server.tournament.services.CreateTournamentService;
import com.example.server.usercredentials.exception.InvalidFieldException;
import com.example.server.usercredentials.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(basePackageClasses = {Pool.class, TournamentController.class, RegisterUserController.class, AddUserToTournament.class, CreateTournamentService.class, ChangeStatusTournament.class})
public class TournamentControllerAdvice {

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

    @ExceptionHandler(InvalidStatusTournamentException.class)
    public ResponseEntity<String> invalidStatusTournamentExistException(Exception e) {
        log.info(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getMessage());
    }

    @ExceptionHandler(InvalidFieldException.class)
    public ResponseEntity<String> invalidFieldExistException(Exception e) {
        log.info(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getMessage());
    }

    @ExceptionHandler(PoolIsEmpty.class)
    public ResponseEntity<String> pollIsEmptyException(Exception e) {
        log.info(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getMessage());
    }

    @ExceptionHandler(UserWasRegisterInThisTournament.class)
    public ResponseEntity<String> userWasRegisterInThisTournamentException(Exception e) {
        log.info(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getMessage());
    }


}
