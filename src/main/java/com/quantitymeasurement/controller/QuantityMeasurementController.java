package com.quantitymeasurement.controller;

import com.quantitymeasurement.enumeration.Unit;
import com.quantitymeasurement.exception.QuantityMeasurementException;
import com.quantitymeasurement.model.QuantityMeasurement;
import com.quantitymeasurement.service.QuantityMeasurementService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuantityMeasurementController {

    @Autowired
    QuantityMeasurementService quantityMeasurementService;

    @ApiResponses(value = {@ApiResponse(code = 400,message = "Bad Request")})

    @GetMapping("/quantity-measurement/{unit}/{value}/{requiredUnit}")
    public QuantityMeasurement getConvertedValue(@PathVariable("unit") String unit, @PathVariable("value") Double value,
                                                 @PathVariable("requiredUnit") String requiredUnit) {
        try {
            return new QuantityMeasurement(quantityMeasurementService.convertTo(new QuantityMeasurement(
                                                                                value,Unit.valueOf(unit.toUpperCase())),
                                                                                Unit.valueOf(requiredUnit.toUpperCase())),
                                           Unit.valueOf(requiredUnit.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new QuantityMeasurementException("Invalid Unit");
        } catch (QuantityMeasurementException e) {
            throw new QuantityMeasurementException(requiredUnit.toUpperCase() + " cannot convert to "+ unit.toUpperCase());
        }
    }

}
