package com.kondratiuk.spring.springboot_rest.ForErrors;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
