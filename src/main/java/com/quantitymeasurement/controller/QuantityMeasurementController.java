package com.quantitymeasurement.controller;

import com.quantitymeasurement.enumeration.Unit;
import com.quantitymeasurement.model.QuantityMeasurement;
import com.quantitymeasurement.service.QuantityMeasurementService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@RestController
public class QuantityMeasurementController {

    @Autowired
    QuantityMeasurementService quantityMeasurementService;

    @ApiResponses(value = {@ApiResponse(code = 400,message = "Bad Request")})

    @GetMapping(value ="/quantity-measurement/{unit}/{value}/{requiredUnit}",
               produces = {APPLICATION_JSON_VALUE,APPLICATION_XML_VALUE})
    public QuantityMeasurement getConvertedValue(@PathVariable("unit") Unit unit, @PathVariable("value") Double value,
                                                 @PathVariable("requiredUnit") Unit requiredUnit) {
        return new QuantityMeasurement(quantityMeasurementService.convertTo(new QuantityMeasurement(value,unit),
                                                                                requiredUnit),requiredUnit);
    }

}
