package com.quantitymeasurement.exception;

import com.quantitymeasurement.enumeration.Message;
import org.springframework.http.HttpStatus;

public class QuantityMeasurementException extends RuntimeException {

    Message exceptionType;
    HttpStatus status;

    public QuantityMeasurementException(String message,Message exceptionType,HttpStatus status) {
        super(message);
        this.exceptionType = exceptionType;
        this.status = status;
    }

}
