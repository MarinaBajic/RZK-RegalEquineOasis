package com.reo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ErrorEntity> handleEntityAlreadyExists(EntityAlreadyExistsException ex) {
        ErrorEntity errorEntity = new ErrorEntity(ex.getMessage(), ex.getIdEntity());
        return new ResponseEntity<>(errorEntity, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityDoesNotExistException.class)
    public ResponseEntity<ErrorEntity> handleEntityDoesNotExists(EntityDoesNotExistException ex) {
        ErrorEntity errorEntity = new ErrorEntity(ex.getMessage(), ex.getIdEntity());
        return new ResponseEntity<>(errorEntity, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnableToAddNewEntityException.class)
    public ResponseEntity<ErrorEntity> handleUnableToAddNewEntity(UnableToAddNewEntityException ex) {
        ErrorEntity errorEntity = new ErrorEntity(ex.getMessage(), -1);
        return new ResponseEntity<>(errorEntity, HttpStatus.BAD_REQUEST);
    }

}
