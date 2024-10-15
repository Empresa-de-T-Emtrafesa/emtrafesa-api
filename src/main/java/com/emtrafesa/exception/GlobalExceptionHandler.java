package com.emtrafesa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
   // @ExceptionHandler(Exception.class)
    //public ResponseEntity<?> handleGlobalException(Exception ex) {
       // Map<String, String> error = new HashMap<>();
       // error.put("message", "Ocurrió un error inesperado.");
       // return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
   // }
}
