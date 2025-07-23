package com.rekahdo.ecommerce.goodshop.exceptions.handlers;

import com.rekahdo.project.spring_boot.exceptions.classes.EmptyListException;
import com.rekahdo.project.spring_boot.exceptions.classes.UserIdNotFoundException;
import com.rekahdo.project.spring_boot.exceptions.classes.UsernameExistException;
import com.rekahdo.project.spring_boot.exceptions.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class Api_ExceptionHandler_User_Defined {

    // API DEFINED EXCEPTIONS
    @ExceptionHandler(UserIdNotFoundException.class)
    public ResponseEntity<?> handleUserIdNotFoundException(UserIdNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(ex, HttpStatus.NOT_FOUND, request);
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponse.fetchMJV());
    }

    @ExceptionHandler(UsernameExistException.class)
    public ResponseEntity<?> handleUsernameExistException(UsernameExistException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(ex, HttpStatus.CONFLICT, request);
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponse.fetchMJV());
    }

    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<?> handleEmptyListException(EmptyListException ex, WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse(ex, HttpStatus.NO_CONTENT, request);
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponse.fetchMJV());
    }

}