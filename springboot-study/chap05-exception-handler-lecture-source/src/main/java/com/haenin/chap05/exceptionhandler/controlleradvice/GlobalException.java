package com.haenin.chap05.exceptionhandler.controlleradvice;

public class GlobalException extends RuntimeException{
    public GlobalException(String message) {
        super(message);
    }
}
