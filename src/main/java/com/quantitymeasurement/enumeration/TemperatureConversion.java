package com.quantitymeasurement.enumeration;

import java.util.Arrays;
import java.util.List;

public enum TemperatureConversion {

    // Conversion from kelvin to fahrenheit and kelvin to celcius

    FAHRENHEIT(9.0/5.0,-273.15,32.0), CELCIUS(1.0,-273.15,0.0);

    private final List<Double> conversionValue;

    TemperatureConversion(Double ...conversionValue) {
        this.conversionValue = Arrays.asList(conversionValue);
    }

    public double getConvertedValue(double value) {
        return conversionValue.get(0)*(value+ conversionValue.get(1)) + conversionValue.get(2);
    }

}
