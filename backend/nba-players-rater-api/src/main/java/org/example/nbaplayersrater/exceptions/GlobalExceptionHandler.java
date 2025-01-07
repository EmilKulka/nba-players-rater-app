package org.example.nbaplayersrater.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errorMessages = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errorMessages.add(errorMessage);
        });
        ErrorDetails errorDetails = new ErrorDetails(
                HttpStatus.BAD_REQUEST.name(),
                LocalDateTime.now(),
                errorMessages,
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MatchupAlreadyAnsweredException.class)
    public ResponseEntity<ErrorDetails> handleMatchupAlreadyAnsweredException(MatchupAlreadyAnsweredException ex) {
        List<String> errorMessages = new ArrayList<>();
        errorMessages.add(ex.getMessage());

        ErrorDetails errorDetails = new ErrorDetails(
                HttpStatus.BAD_REQUEST.name(),
                LocalDateTime.now(),
                errorMessages,
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MatchupDoesNotExistException.class)
    public ResponseEntity<ErrorDetails> handleMatchupDoesNotExistException(MatchupDoesNotExistException ex) {
        List<String> errorMessages = new ArrayList<>();
        errorMessages.add(ex.getMessage());

        ErrorDetails errorDetails = new ErrorDetails(
                HttpStatus.BAD_REQUEST.name(),
                LocalDateTime.now(),
                errorMessages,
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PlayerDidNotParticipateInMatchupException.class)
    public ResponseEntity<ErrorDetails> handlePlayerDidNotParticipateInMatchupException(PlayerDidNotParticipateInMatchupException ex) {
        List<String> errorMessages = new ArrayList<>();
        errorMessages.add(ex.getMessage());

        ErrorDetails errorDetails = new ErrorDetails(
                HttpStatus.BAD_REQUEST.name(),
                LocalDateTime.now(),
                errorMessages,
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PlayerDoesNotExistException.class)
    public ResponseEntity<ErrorDetails> handlePlayerDoesNotParticipateInMatchupException(PlayerDoesNotExistException ex) {
        List<String> errorMessages = new ArrayList<>();
        errorMessages.add(ex.getMessage());

        ErrorDetails errorDetails = new ErrorDetails(
                HttpStatus.BAD_REQUEST.name(),
                LocalDateTime.now(),
                errorMessages,
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
