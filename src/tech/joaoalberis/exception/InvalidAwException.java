package tech.joaoalberis.exception;

public class InvalidAwException extends Exception{

    public InvalidAwException(String message) {
        super(message);
    }

    public InvalidAwException(String message, Throwable cause) {
        super(message, cause);
    }
}
