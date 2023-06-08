package com.book.infra.exception;

/**
 * @author jinx
 */
public class UnprocessableException extends RuntimeException {

    public UnprocessableException(String message) {
        super(message);
    }
}
