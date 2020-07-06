package com.quantitymeasurement.model;

import com.quantitymeasurement.enumeration.Unit;

public class QuantityMeasurement {

    private Double value;
    private Unit unit;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public QuantityMeasurement(Double value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

}
