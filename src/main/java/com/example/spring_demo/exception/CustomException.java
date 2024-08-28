package com.example.spring_demo.exception;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }

}
