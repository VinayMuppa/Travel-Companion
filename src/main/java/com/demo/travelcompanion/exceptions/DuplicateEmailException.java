package com.demo.travelcompanion.exceptions;

@SuppressWarnings("serial")
public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException() {
        super();
    }

    public DuplicateEmailException(String message) {
        super(message);
    }

}
