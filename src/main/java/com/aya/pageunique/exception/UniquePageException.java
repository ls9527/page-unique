package com.aya.pageunique.exception;

/**
 * 唯一建翻页异常
 */
public class UniquePageException extends RuntimeException {
    public UniquePageException() {
    }

    public UniquePageException(String message) {
        super(message);
    }

    public UniquePageException(String message, Throwable cause) {
        super(message, cause);
    }

    public UniquePageException(Throwable cause) {
        super(cause);
    }

    public UniquePageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
