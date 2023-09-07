package com.inderjeet.springBootDemo.exception;

import com.inderjeet.springBootDemo.exception.model.CustomErrorResponse;
import com.inderjeet.springBootDemo.exception.model.ValidationError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ValidationError> errors = ex.getFieldErrors().stream()
                .map(error -> ValidationError.builder()
                        .name(error.getField())
                        .message(error.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(CustomErrorResponse.builder()
                .errors(errors)
                .description(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .code(HttpStatus.BAD_REQUEST.value()).build());
    }

}
