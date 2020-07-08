package com.quantitymeasurement.exception;

import com.quantitymeasurement.enumeration.Message;
import com.quantitymeasurement.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

import static com.quantitymeasurement.enumeration.Message.INVALID_INPUT;

@ControllerAdvice
public class QuantityMeasurementExceptionHandler {

    @Autowired
    Response response;

    @ExceptionHandler
    public ResponseEntity<Response> addException(QuantityMeasurementException e){
        return getResponseEntity(e.exceptionType,e.status);
    }

    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<Response> addException(){
        return getResponseEntity(INVALID_INPUT,HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Response> getResponseEntity(Message message,HttpStatus status){
        response.setMessage(message);
        response.setStatus(status.value());
        return new ResponseEntity<>(response, status);
    }

}
