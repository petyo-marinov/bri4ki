package bri4ka.exceptions;

import java.io.IOException;

public class FileHandlingException extends RuntimeException{

    public FileHandlingException(String message, Exception e){
        super(message);
    }
}
