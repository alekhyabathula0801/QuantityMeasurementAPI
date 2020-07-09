package com.quantitymeasurement.service;

import com.quantitymeasurement.enumeration.Measurement;
import com.quantitymeasurement.enumeration.Message;
import com.quantitymeasurement.enumeration.TemperatureConversion;
import com.quantitymeasurement.enumeration.Unit;
import com.quantitymeasurement.exception.QuantityMeasurementException;
import com.quantitymeasurement.model.QuantityMeasurement;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.quantitymeasurement.enumeration.Measurement.TEMPERATURE;

@Service
public class QuantityMeasurementService {

    /**
     *
     * @param quantityMeasurement contains quantity and unit
     * @param unit is required unit to convert
     * @return converted value of given quantity measurement
     */
    public Double convertTo(QuantityMeasurement quantityMeasurement, Unit unit) {
        if(quantityMeasurement.getUnit().getMeasurementType() != unit.getMeasurementType())
            throw new QuantityMeasurementException(quantityMeasurement.getUnit()+" cannot convert to "+unit,
                                                   Message.INVALID_CONVERSION, HttpStatus.BAD_REQUEST);
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

    /**
     *
     * @param measurementType
     * @return list of available Units of given measurement type
     */
    public List<Unit> getUnits(Measurement measurementType) {
        return Arrays.stream(Unit.values())
                     .filter(measurement->measurement.getMeasurementType().equals(measurementType))
                     .collect(Collectors.toList());
    }

    /**
     *
     * @return list of available measurement
     */
    public List<Measurement> getMeasurementTypes() {
        return Arrays.stream(Measurement.values()).collect(Collectors.toList());
    }

}
