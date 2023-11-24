package com.andreidodu.elisyshomeautomation.exception;

public class ApplicationException extends RuntimeException {

    public ApplicationException(Throwable throwable) {
        super(throwable);
    }

    public ApplicationException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
