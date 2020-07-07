package com.quantitymeasurement.exception;

import com.quantitymeasurement.model.Response;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class QuantityMeasurementExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Response> addException(QuantityMeasurementException e){
        return getResponseEntity(e.getMessage());
    }

    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<Response> addException(){
        return getResponseEntity("Invalid Unit");
    }

    public ResponseEntity<Response> getResponseEntity(String message){
        Response response = new Response(message);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
