package com.jcoronado.bazar.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidation(MethodArgumentNotValidException exception){

        List<String> errores = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .toList();

        return ResponseEntity.badRequest().body(Map.of(
                "status", 400,
                "error" , "Validacion fallida",
                "detalles", errores
        ));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(ResourceNotFoundException exception){
        return ResponseEntity.status(404).body(Map.of(
                "status", 404,
                "error", exception.getMessage()
        ));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequest(BadRequestException exception){
        return ResponseEntity.badRequest().body(Map.of(
                "status", 400,
                "error", exception.getMessage()
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneral(Exception exception){
        return ResponseEntity.status(500).body(Map.of(
                "status", 500,
                "error", "Error interno del servidor"
        ));
    }

}
