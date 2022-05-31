package com.example.SeamHealthAssessment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundEx(UserNotFoundException ex, WebRequest request){
        ApiErrorDetail errorDetail = new ApiErrorDetail(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<?> handleAddressNotFoundEx(AddressNotFoundException ex, WebRequest request){
        ApiErrorDetail errorDetail = new ApiErrorDetail(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

}
