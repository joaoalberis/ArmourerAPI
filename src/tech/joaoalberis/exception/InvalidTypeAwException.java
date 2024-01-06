package tech.joaoalberis.exception;

public class InvalidTypeAwException extends Exception{

    public InvalidTypeAwException(String message) {
        super(message);
    }

    public InvalidTypeAwException(String message, Throwable cause) {
        super(message, cause);
    }
}
