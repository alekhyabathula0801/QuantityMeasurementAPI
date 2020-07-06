package com.quantitymeasurement.controller;

import com.quantitymeasurement.enumeration.Unit;
import com.quantitymeasurement.exception.QuantityMeasurementException;
import com.quantitymeasurement.model.QuantityMeasurement;
import com.quantitymeasurement.service.QuantityMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuantityMeasurementController {

    @Autowired
    QuantityMeasurementService quantityMeasurementService;

    @GetMapping("/quantity-measurement/{unit}")
    public QuantityMeasurement getConvertedValue(@PathVariable("unit") String unit, QuantityMeasurement quantityMeasurement) {
        try {
            return new QuantityMeasurement(quantityMeasurementService.convertTo(quantityMeasurement, Unit.valueOf(unit.toUpperCase())), Unit.valueOf(unit.toUpperCase()));
        } catch (ConversionFailedException e) {
            throw new QuantityMeasurementException("Invalid Unit");
        }
    }

}
