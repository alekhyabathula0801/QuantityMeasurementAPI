package com.quantitymeasurement.service;

import com.quantitymeasurement.enumeration.Measurement;
import com.quantitymeasurement.enumeration.TemperatureConversion;
import com.quantitymeasurement.enumeration.Unit;
import com.quantitymeasurement.exception.QuantityMeasurementException;
import com.quantitymeasurement.model.QuantityMeasurement;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.quantitymeasurement.enumeration.Measurement.TEMPERATURE;

@Service
public class QuantityMeasurementService {

    public Double convertTo(QuantityMeasurement quantityMeasurement, Unit unit) {
        if(quantityMeasurement.getUnit().getMeasurementType() != unit.getMeasurementType())
            throw new QuantityMeasurementException(quantityMeasurement.getUnit()+" cannot convert to "+unit);
        if(unit.getMeasurementType() != TEMPERATURE) {
            Double baseUnitConvertedValue = quantityMeasurement.getUnit()
                                                               .getConvertedValue(quantityMeasurement.getValue(),
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

    public List<Unit> getUnits(Measurement measurementType) {
        return Arrays.stream(Unit.values())
                     .filter(measurement->measurement.getMeasurementType().equals(measurementType))
                     .collect(Collectors.toList());
    }

}
