package com.diploma.edu.source.exceptions;

public class IncorrectStatusException extends RuntimeException{

    public IncorrectStatusException() {
        super();
    }

    public IncorrectStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectStatusException(String message) {
        super(message);
    }

    public IncorrectStatusException(Throwable cause) {
        super(cause);
    }

}
