package com.springboot.learning.exception;

public class UsDefException extends Exception{

    private String errorCode;
    private String message;

    public UsDefException(String message) {
        this.message = message;
        this.errorCode = "0";
    }

    public UsDefException(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public UsDefException(String errorCode, String message, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
