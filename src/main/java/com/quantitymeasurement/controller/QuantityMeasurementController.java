package com.quantitymeasurement.controller;

import com.quantitymeasurement.enumeration.Measurement;
import com.quantitymeasurement.enumeration.Unit;
import com.quantitymeasurement.exception.QuantityMeasurementException;
import com.quantitymeasurement.model.QuantityMeasurement;
import com.quantitymeasurement.service.QuantityMeasurementService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@RestController
@RequestMapping(produces = {APPLICATION_JSON_VALUE,APPLICATION_XML_VALUE})
public class QuantityMeasurementController {

    @Autowired
    QuantityMeasurementService quantityMeasurementService;

    @ApiResponses(value = {@ApiResponse(code = 400,message = "Bad Request")})

    @GetMapping("/quantity-measurement/{unit}/{value}/{requiredUnit}")
    public QuantityMeasurement getConvertedValue(@PathVariable("unit") Unit unit, @PathVariable("value") Double value,
                                                 @PathVariable("requiredUnit") Unit requiredUnit) {
        return new QuantityMeasurement(quantityMeasurementService.convertTo(new QuantityMeasurement(value,unit),
                                                                                requiredUnit),requiredUnit);
    }

    @GetMapping("/quantity-measurement/{measurementType}")
    public List<Unit> getUnits(@PathVariable("measurementType") String measurementType) {
        try {
            return quantityMeasurementService.getUnits(Measurement.valueOf(measurementType.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new QuantityMeasurementException(measurementType.toUpperCase() + " is Invalid Measurement");
        }
    }

}
