package com.example.supportPortal.exceptions.domain;

public class UserNameExistException extends Exception {
    public UserNameExistException(String message) {
        super(message);
    }
}
