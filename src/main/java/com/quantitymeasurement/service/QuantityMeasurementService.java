package com.quantitymeasurement.service;

import com.quantitymeasurement.enumeration.TemperatureConversion;
import com.quantitymeasurement.enumeration.Unit;
import com.quantitymeasurement.exception.QuantityMeasurementException;
import com.quantitymeasurement.model.QuantityMeasurement;
import org.springframework.stereotype.Service;

@Service
public class QuantityMeasurementService {

    public Double convertTo(QuantityMeasurement quantityMeasurement, Unit unit) {
        if(quantityMeasurement.getUnit().getMeasurementType() != unit.getMeasurementType())
            throw new QuantityMeasurementException("Invalid conversion");
        if(unit.getMeasurementType() != "TEMPERATURE") {
            Double baseUnitConvertedValue = quantityMeasurement.getUnit().getConvertedValue(quantityMeasurement.getValue(),
                                                                                            quantityMeasurement.getUnit());
            return baseUnitConvertedValue/unit.getConversionValue();
        }
        double kelvinValue = quantityMeasurement.getUnit().getConvertedValue(quantityMeasurement.getValue(),
                                                                             quantityMeasurement.getUnit());
        if(unit.equals(Unit.FAHRENHEIT))
            return TemperatureConversion.FAHRENHEIT.getConvertedValue(kelvinValue);
        if(unit.equals(Unit.CELCIUS))
            return TemperatureConversion.CELCIUS.getConvertedValue(kelvinValue);
        return kelvinValue;
    }

}
