package com.quantitymeasurement.controller;

import com.quantitymeasurement.enumeration.Unit;
import com.quantitymeasurement.exception.QuantityMeasurementException;
import com.quantitymeasurement.model.QuantityMeasurement;
import com.quantitymeasurement.service.QuantityMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuantityMeasurementController {

    @Autowired
    QuantityMeasurementService quantityMeasurementService;

    @GetMapping("/quantity-measurement/{unitIn}/{value}/{unitOut}")
    public QuantityMeasurement getConvertedValue(@PathVariable("unitIn") String unitIn,@PathVariable("value") Double value,
                                                 @PathVariable("unitOut") String unitOut) {
        try {
            return new QuantityMeasurement(quantityMeasurementService.convertTo(new QuantityMeasurement(
                                                                                value,Unit.valueOf(unitIn.toUpperCase())),
                                                                                Unit.valueOf(unitOut.toUpperCase())),
                                            Unit.valueOf(unitOut.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new QuantityMeasurementException("Invalid Unit");
        } catch (QuantityMeasurementException e) {
            throw new QuantityMeasurementException(unitOut + " cann't convert to "+ unitIn);
        }
    }

}
