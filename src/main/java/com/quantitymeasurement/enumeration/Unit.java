package com.quantitymeasurement.enumeration;

import java.util.Arrays;
import java.util.List;

public enum Unit {

    FEET("LENGTH",304.8), INCH("LENGTH",25.4), YARD("LENGTH",914.4),CENTIMETRE("LENGTH",10.0), METRE("LENGTH",1000.0),
    KILOMETRE("LENGTH",1000000.0), MILLIMETRE("LENGTH",1.0), LITRE("VOLUME",1000.0), GALLON("VOLUME",3785.41),
    MILLILITRE("VOLUME",1.0), GRAM("MASS",1.0), KILOGRAM("MASS",1000.0), TONNE("MASS",1000000.0),
    KELVIN("TEMPERATURE",1.0,0.0,0.0), CELCIUS("TEMPERATURE",1.0,273.15,0.0), FAHRENHEIT("TEMPERATURE",5.0/9.0,-32.0,273.15);

    private final String measurementType;
    private final List<Double> conversionValue;

    Unit(String measurementType,Double... conversionValue) {
        this.measurementType = measurementType;
        this.conversionValue = Arrays.asList(conversionValue);
    }

    public double getConvertedValue(double value,Unit unit) {
        if(unit.measurementType != "TEMPERATURE")
            return conversionValue.get(0)*value;
        return conversionValue.get(0)*(value+ conversionValue.get(1)) + conversionValue.get(2);
    }

    public Double getConversionValue() {
        return conversionValue.get(0);
    }

    public String getMeasurementType() {
        return measurementType;
    }

}
