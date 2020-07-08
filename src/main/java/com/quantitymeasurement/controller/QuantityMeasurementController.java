package com.quantitymeasurement.controller;

import com.quantitymeasurement.enumeration.Measurement;
import com.quantitymeasurement.enumeration.Unit;
import com.quantitymeasurement.model.QuantityMeasurement;
import com.quantitymeasurement.model.Response;
import com.quantitymeasurement.service.QuantityMeasurementService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.quantitymeasurement.enumeration.Message.SUCCESSFUL;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@RestController
@RequestMapping(produces = {APPLICATION_JSON_VALUE,APPLICATION_XML_VALUE})
public class QuantityMeasurementController {

    @Autowired
    QuantityMeasurementService quantityMeasurementService;

    @ApiResponses(value = {@ApiResponse(code = 400,message = "Bad Request")})
    @GetMapping("/quantity-measurement/{unit}/{value}/{requiredUnit}")
    public ResponseEntity<Response> getConvertedValue(@PathVariable("unit") Unit unit, @PathVariable("value") Double value,
                                                      @PathVariable("requiredUnit") Unit requiredUnit) {
        double result = quantityMeasurementService.convertTo(new QuantityMeasurement(value, unit), requiredUnit);
        return new ResponseEntity<>(new Response(result, SUCCESSFUL, 200),HttpStatus.OK);
    }

    @GetMapping("/quantity-measurement/{measurementType}")
    public ResponseEntity<Response> getUnits(@PathVariable("measurementType") Measurement measurementType) {
        List<Unit> units = quantityMeasurementService.getUnits(measurementType);
        return new ResponseEntity<>(new Response(units,SUCCESSFUL,200),HttpStatus.OK);
    }

    @GetMapping("/quantity-measurement")
    public ResponseEntity<Response> getMeasurements() {
        List<Measurement> measurementTypes = quantityMeasurementService.getMeasurementTypes();
        return new ResponseEntity<>(new Response(measurementTypes,SUCCESSFUL,200),HttpStatus.OK);
    }

}
