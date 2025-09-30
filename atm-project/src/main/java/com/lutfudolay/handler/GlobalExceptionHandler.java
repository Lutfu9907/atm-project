package com.lutfudolay.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lutfudolay.exception.BaseException;
import com.lutfudolay.exception.ErrorMessage;
import com.lutfudolay.exception.MessageType;

@ControllerAdvice
public class GlobalExceptionHandler {

	 @ExceptionHandler(BaseException.class)
	    public ResponseEntity<ErrorMessage> handleBaseException(BaseException ex) {
	        ErrorMessage error = new ErrorMessage(
	                LocalDateTime.now(),
	                ex.getMessage(),
	                ex.getType(),
	                "Özel hata"
	        );
	        return new ResponseEntity<>(error, ex.getStatus());
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorMessage> handleGeneralException(Exception ex) {
	        ErrorMessage error = new ErrorMessage(
	                LocalDateTime.now(),
	                ex.getMessage(),
	                MessageType.GENERAL_ERROR,
	                "Beklenmedik hata"
	        );
	        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<ErrorMessage> handleValidationException(MethodArgumentNotValidException ex) {
	        String msg = ex.getBindingResult().getFieldErrors().stream()
	                       .map(err -> err.getField() + ": " + err.getDefaultMessage())
	                       .findFirst()
	                       .orElse("Geçersiz parametre");

	        ErrorMessage error = new ErrorMessage(
	                LocalDateTime.now(),
	                msg,
	                MessageType.VALIDATION_ERROR,
	                "Parametre doğrulama hatası"
	        );
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    }

}
