package bri4ka.controller;

import bri4ka.exceptions.*;
import bri4ka.model.dto.ErrorDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;

@Log4j2
public class AbstractController {

    @ExceptionHandler(NotAcceptableException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErrorDTO handleException(NotAcceptableException e) {
        log.error(e.getMessage());
        return new ErrorDTO("The request is not acceptable. " + e.getMessage());
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

    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorDTO handleException(AuthorizationException e){
        log.error(e.getMessage());
        return new ErrorDTO("The operation is forbidden, "+e.getMessage());
    }
    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(HttpServerErrorException.InternalServerError e){
        log.error(e.getMessage());
        return new ErrorDTO("We are sorry,  " + e.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(BadRequestException e){
        log.error(e.getMessage());
        return new ErrorDTO("Sorry, " + e.getMessage());
    }
}
