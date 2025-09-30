package com.lutfudolay.exception;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException{

	private final MessageType type;
	private final HttpStatus status;

    public BaseException(String message, MessageType type, HttpStatus status) {
        super(message);
        this.type = type;
        this.status = status;
    }

    public MessageType getType() {
        return type;
    }
    
    public HttpStatus getStatus() {
    	return status;
    }
}
