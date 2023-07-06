package bri4ka.exceptions;

public class ImageProcessingException extends RuntimeException{
    public ImageProcessingException(String msg, Exception e){
        super(msg, e);
    }
}
