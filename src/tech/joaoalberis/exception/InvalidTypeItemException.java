package tech.joaoalberis.exception;

public class InvalidTypeItemException extends Exception{

    public InvalidTypeItemException(String message) {
        super(message);
    }

    public InvalidTypeItemException(String message, Throwable cause) {
        super(message, cause);
    }
}
