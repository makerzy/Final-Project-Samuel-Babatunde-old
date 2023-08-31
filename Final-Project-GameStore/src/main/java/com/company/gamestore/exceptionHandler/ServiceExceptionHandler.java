package com.company.gamestore.exceptionHandler;

import com.company.gamestore.exception.*;
import com.company.gamestore.model.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ServiceExceptionHandler {
    private static CustomErrorResponse errorRes;

    @ExceptionHandler(InvalidQuantityException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<CustomErrorResponse> handleInvalidQuantityException(InvalidQuantityException e) {
        errorRes = new CustomErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.toString(), e.getMessage());
        errorRes.setStatus((HttpStatus.UNPROCESSABLE_ENTITY.value()));
        errorRes.setTimestamp(LocalDateTime.now());
        ResponseEntity<CustomErrorResponse> responseEntity = new ResponseEntity<>(errorRes, HttpStatus.UNPROCESSABLE_ENTITY);
        return responseEntity;
    }

    @ExceptionHandler(UnknownStateCodeException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<CustomErrorResponse> handleUnknownStateCodeException(UnknownStateCodeException e) {
        errorRes = new CustomErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.toString(), e.getMessage());
        errorRes.setStatus((HttpStatus.UNPROCESSABLE_ENTITY.value()));
        errorRes.setTimestamp(LocalDateTime.now());
        ResponseEntity<CustomErrorResponse> responseEntity = new ResponseEntity<>(errorRes, HttpStatus.UNPROCESSABLE_ENTITY);
        return responseEntity;
    }

    @ExceptionHandler(IdMismatchException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<CustomErrorResponse> handleIdMismatchException(IdMismatchException e) {
        errorRes = new CustomErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.toString(), e.getMessage());
        errorRes.setStatus((HttpStatus.UNPROCESSABLE_ENTITY.value()));
        errorRes.setTimestamp(LocalDateTime.now());
        ResponseEntity<CustomErrorResponse> responseEntity = new ResponseEntity<>(errorRes, HttpStatus.UNPROCESSABLE_ENTITY);
        return responseEntity;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<CustomErrorResponse> handleNotFoundException(NotFoundException e) {
        errorRes = new CustomErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.toString(), e.getMessage());
        errorRes.setStatus((HttpStatus.UNPROCESSABLE_ENTITY.value()));
        errorRes.setTimestamp(LocalDateTime.now());
        ResponseEntity<CustomErrorResponse> responseEntity = new ResponseEntity<>(errorRes, HttpStatus.UNPROCESSABLE_ENTITY);
        return responseEntity;
    }

//    @ExceptionHandler(value = IllegalArgumentException.class) //for business logic validation
//    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
//    public ResponseEntity<CustomErrorResponse> handleGenericNotFoundException(IllegalArgumentException e) {
//        CustomErrorResponse errorRes = new CustomErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.toString(), e.getMessage());
//        errorRes.setStatus((HttpStatus.UNPROCESSABLE_ENTITY.value()));
//        errorRes.setTimestamp(LocalDateTime.now());
//        ResponseEntity<CustomErrorResponse> responseEntity = new ResponseEntity<>(errorRes, HttpStatus.UNPROCESSABLE_ENTITY);
//        return responseEntity;
//    }
}
