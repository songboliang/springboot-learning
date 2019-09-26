package com.springboot.learning.exception;

public class JSchException extends Exception {
    private Throwable cause = null;

    public JSchException() {
    }

    public JSchException(String s) {
        super(s);
    }

    public JSchException(String s, Throwable e) {
        super(s);
        this.cause = e;
    }

    public Throwable getCause() {
        return this.cause;
    }
}
