package com.azizi.bank.exception;

public class AccessDeniedException extends RuntimeException {

    private static final long serialVersionUID = -1L;
    private static final String MESSAGE = "Access denied!";

    public AccessDeniedException() {
        super(MESSAGE);
    }

}
