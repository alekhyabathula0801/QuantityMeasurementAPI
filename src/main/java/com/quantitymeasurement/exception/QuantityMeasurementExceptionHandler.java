package com.quantitymeasurement.exception;

import com.quantitymeasurement.enumeration.Message;
import com.quantitymeasurement.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static com.quantitymeasurement.enumeration.Message.INVALID_INPUT;
import static com.quantitymeasurement.enumeration.Message.TRY_AGAIN_LATER;

@RestControllerAdvice
public class QuantityMeasurementExceptionHandler {

    @Autowired
    Response response;

    /**
     *
     * @param e contains error message and HTTP Status
     * @return Response Entity with message and status
     */
    @ExceptionHandler(QuantityMeasurementException.class)
    public ResponseEntity<Response> addException(QuantityMeasurementException e){
        return getResponseEntity(e.exceptionType,e.status);
    }

    /**
     *
     * @return Response Entity with message and status
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Response> addException(){
        return getResponseEntity(INVALID_INPUT,HttpStatus.BAD_REQUEST);
    }

    /**
     *
     * @return Response Entity with message and status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> addCustomException(){
        return getResponseEntity(TRY_AGAIN_LATER,HttpStatus.NOT_FOUND);
    }

    /**
     *
     * @param message is Exception Message
     * @param status is Http status
     * @return Response Entity with message and status
     */
    public ResponseEntity<Response> getResponseEntity(Message message,HttpStatus status){
        response.setMessage(message);
        response.setStatus(status.value());
        return new ResponseEntity<>(response, status);
    }

}
