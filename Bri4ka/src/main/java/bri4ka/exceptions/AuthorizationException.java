package bri4ka.exceptions;

public class AuthorizationException extends RuntimeException{

    public AuthorizationException(String reason){
        super(reason);
    }
}
