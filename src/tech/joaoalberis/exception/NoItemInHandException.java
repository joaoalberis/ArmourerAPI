package tech.joaoalberis.exception;

public class NoItemInHandException extends Exception{

    public NoItemInHandException(String message) {
        super(message);
    }

    public NoItemInHandException(String message, Throwable cause) {
        super(message, cause);
    }
}
