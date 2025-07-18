package com.rekahdo.ecommerce.goodshop.exceptions.handlers;

import com.rekahdo.ecommerce.goodshop.exceptions.model.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.rekahdo.ecommerce.goodshop.enums.ErrorCode.INVALID_DATA_IN_FIELD_VIOLATION;

@RestControllerAdvice
public class Api_ResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(INVALID_DATA_IN_FIELD_VIOLATION, ex, request);
        return ResponseEntity.status(status).body(errorResponse.fetchMJV());
    }



}