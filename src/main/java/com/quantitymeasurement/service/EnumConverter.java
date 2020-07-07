package com.quantitymeasurement.service;

import com.quantitymeasurement.enumeration.Unit;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EnumConverter implements Converter<String, Unit> {

    @Override
    public Unit convert(String unit) {
        return Unit.valueOf(unit.toUpperCase());
    }

}
