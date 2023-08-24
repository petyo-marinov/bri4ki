package bri4ka.controller;

import bri4ka.exceptions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractControllerTest {

    private AbstractController abstractController = new AbstractController();

    @Test
    void itShouldHandleNotAcceptableException() {
        assertEquals("The request is not acceptable. ",
                abstractController.handleException(new NotAcceptableException("")).getMessage());
    }

    @Test
    void itShouldHandleNotFoundException() {
        assertEquals("",
                abstractController.handleException(new NotFoundException("")).getMessage());
    }

    @Test
    void itShouldHandleInvalidCredentialsException() {
        assertEquals("Invalid credentials. test",
                abstractController.handleException(new InvalidCredentialsException("test")).getMessage());
    }

    @Test
    void itShouldHandleAuthorizationException() {
        assertEquals("The operation is forbidden, You don't have permission.",
                abstractController.handleException(new AuthorizationException("You don't have permission.")).getMessage());
    }

    @Test
    void itShouldHandleBadRequestException() {
        assertEquals("Sorry! ",
                abstractController.handleException(new BadRequestException("")).getMessage());
    }
}