package bri4ka.controller;

import bri4ka.exceptions.InvalidCredentialsException;
import bri4ka.exceptions.NotAcceptableException;
import bri4ka.exceptions.NotFoundException;
import bri4ka.model.dto.ErrorDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Log4j2
public class AbstractController {

    @ExceptionHandler(NotAcceptableException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErrorDTO handleException(NotAcceptableException e) {
        log.error(e.getMessage());
        return new ErrorDTO("Please try again, "  + e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(NotFoundException e){
        log.error(e.getMessage());
        return new ErrorDTO(e.getMessage());
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDTO handleException(InvalidCredentialsException e){
        log.error(e.getMessage());
        return new ErrorDTO("Invalid credentials. " + e.getMessage());
    }
}
