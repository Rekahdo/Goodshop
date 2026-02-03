package com.rekahdo.goodshop.address_service.exceptions.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rekahdo.goodshop.address_service.exceptions.model.ErrorResponse;
import feign.FeignException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class Api_ExceptionHandler {

    // API DEFINED EXCEPTIONS
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleResponseStatusException(ResponseStatusException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex);
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponse.view());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<?> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex, HttpStatus.CONFLICT);
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse.view());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException(NullPointerException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex, HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse.view());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex);
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse.view());
    }

    @ExceptionHandler(JpaSystemException.class)
    public ResponseEntity<?> handleJpaSystemException(JpaSystemException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex);
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse.view());
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<?> handleUnsupportedOperationException(UnsupportedOperationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex, HttpStatus.NOT_ACCEPTABLE);
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse.view());
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<?> handleHttpMessageConversionException(HttpMessageConversionException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex);
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse.view());
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<?> handleMethodNotAllowedException(MethodNotAllowedException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex);
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse.view());
    }

    @ExceptionHandler({FeignException.NotFound.class})
    public ResponseEntity<?> handleFeignServerErrorException(FeignException ex) throws JsonProcessingException {
        ErrorResponse errorResponse = ErrorResponse.getInstance(ex);
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse.view());
    }

}