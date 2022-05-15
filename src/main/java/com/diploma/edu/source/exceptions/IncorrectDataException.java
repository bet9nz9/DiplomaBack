package com.diploma.edu.source.exceptions;

public class IncorrectDataException extends RuntimeException{

    public IncorrectDataException() {
        super();
    }

    public IncorrectDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectDataException(String message) {
        super(message);
    }

    public IncorrectDataException(Throwable cause) {
        super(cause);
    }

}
