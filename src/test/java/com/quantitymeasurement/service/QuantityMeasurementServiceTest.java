package com.quantitymeasurement.service;

import com.quantitymeasurement.enumeration.Unit;
import com.quantitymeasurement.exception.QuantityMeasurementException;
import com.quantitymeasurement.model.QuantityMeasurement;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class QuantityMeasurementServiceTest {

    @Test
    public void given1Metre_whenConvertedToKilometre_shouldReturnConvertedResults() {
        QuantityMeasurementService quantityMeasurementService = new QuantityMeasurementService();
        double convertedValue = quantityMeasurementService.convertTo(new QuantityMeasurement(1.0, Unit.METRE),Unit.KILOMETRE);
        assertEquals(0.001,convertedValue,0.0);
    }

    @Test
    public void given1Inch_whenConvertedToYard_shouldReturnConvertedResults() {
        QuantityMeasurementService quantityMeasurementService = new QuantityMeasurementService();
        double convertedValue = quantityMeasurementService.convertTo(new QuantityMeasurement(1.0,Unit.INCH),Unit.YARD);
        assertEquals(0.0277778,convertedValue,0.00001);
    }

    @Test
    public void given1Feet_whenConvertedToInch_shouldReturnConvertedResults() {
        QuantityMeasurementService quantityMeasurementService = new QuantityMeasurementService();
        double convertedValue = quantityMeasurementService.convertTo(new QuantityMeasurement(1.0,Unit.FEET),Unit.INCH);
        assertEquals(12,convertedValue,0.00001);
    }

    @Test
    public void given1Centimetre_whenConvertedToMillimetre_shouldReturnConvertedResults() {
        QuantityMeasurementService quantityMeasurementService = new QuantityMeasurementService();
        double convertedValue = quantityMeasurementService.convertTo(new QuantityMeasurement(1.0,Unit.CENTIMETRE),Unit.MILLIMETRE);
        assertEquals(10,convertedValue,0.00001);
    }

    @Test
    public void given1Litre_whenConvertedToYard_shouldThrowException() {
        try {
            QuantityMeasurementService quantityMeasurementService = new QuantityMeasurementService();
            quantityMeasurementService.convertTo(new QuantityMeasurement(1.0, Unit.LITRE), Unit.YARD);
        } catch (QuantityMeasurementException e) {
            assertEquals("Invalid conversion", e.getMessage());
        }
    }

    @Test
    public void given1Litre_whenConvertedToGallon_shouldReturnConvertedResults() {
        QuantityMeasurementService quantityMeasurementService = new QuantityMeasurementService();
        double convertedValue = quantityMeasurementService.convertTo(new QuantityMeasurement(1.0,Unit.LITRE),Unit.GALLON);
        assertEquals(0.264172,convertedValue,0.00001);
    }

    @Test
    public void given1Litre_whenConvertedToMillilitre_shouldReturnConvertedResults() {
        QuantityMeasurementService quantityMeasurementService = new QuantityMeasurementService();
        double convertedValue = quantityMeasurementService.convertTo(new QuantityMeasurement(1.0,Unit.LITRE),Unit.MILLILITRE);
        assertEquals(1000.0,convertedValue,0.0);
    }

    @Test
    public void given1Kilogram_whenConvertedToTonne_shouldReturnConvertedResults() {
        QuantityMeasurementService quantityMeasurementService = new QuantityMeasurementService();
        double convertedValue = quantityMeasurementService.convertTo(new QuantityMeasurement(1.0,Unit.KILOGRAM),Unit.TONNE);
        assertEquals(0.001,convertedValue,0.0);
    }

    @Test
    public void given1Kilogram_whenConvertedToGram_shouldReturnConvertedResults() {
        QuantityMeasurementService quantityMeasurementService = new QuantityMeasurementService();
        double convertedValue = quantityMeasurementService.convertTo(new QuantityMeasurement(1.0,Unit.KILOGRAM),Unit.GRAM);
        assertEquals(1000,convertedValue,0.0);
    }

    @Test
    public void given1Kelvin_whenConvertedToFahrenheit_shouldReturnConvertedResults() {
        QuantityMeasurementService quantityMeasurementService = new QuantityMeasurementService();
        double convertedValue = quantityMeasurementService.convertTo(new QuantityMeasurement(1.0,Unit.KELVIN),Unit.FAHRENHEIT);
        assertEquals(-457.87,convertedValue,0.01);
    }

    @Test
    public void given1Kelvin_whenConvertedToCelcius_shouldReturnConvertedResults() {
        QuantityMeasurementService quantityMeasurementService = new QuantityMeasurementService();
        double convertedValue = quantityMeasurementService.convertTo(new QuantityMeasurement(1.0,Unit.KELVIN),Unit.CELCIUS);
        assertEquals(-272.15,convertedValue,0.01);
    }

    @Test
    public void given1Celcius_whenConvertedToFahrenheit_shouldReturnConvertedResults() {
        QuantityMeasurementService quantityMeasurementService = new QuantityMeasurementService();
        double convertedValue = quantityMeasurementService.convertTo(new QuantityMeasurement(1.0,Unit.CELCIUS),Unit.FAHRENHEIT);
        assertEquals(33.8,convertedValue,0.01);
    }

    @Test
    public void given1Celcius_whenConvertedToKelvin_shouldReturnConvertedResults() {
        QuantityMeasurementService quantityMeasurementService = new QuantityMeasurementService();
        double convertedValue = quantityMeasurementService.convertTo(new QuantityMeasurement(1.0,Unit.CELCIUS),Unit.KELVIN);
        assertEquals(274.15,convertedValue,0.01);
    }

    @Test
    public void given1Fahrenheit_whenConvertedToKelvin_shouldReturnConvertedResults() {
        QuantityMeasurementService quantityMeasurementService = new QuantityMeasurementService();
        double convertedValue = quantityMeasurementService.convertTo(new QuantityMeasurement(1.0,Unit.FAHRENHEIT),Unit.KELVIN);
        assertEquals(255.928,convertedValue,0.001);
    }

    @Test
    public void given1Fahrenheit_whenConvertedToCelcius_shouldReturnConvertedResults() {
        QuantityMeasurementService quantityMeasurementService = new QuantityMeasurementService();
        double convertedValue = quantityMeasurementService.convertTo(new QuantityMeasurement(1.0,Unit.FAHRENHEIT),Unit.CELCIUS);
        assertEquals(-17.2222,convertedValue,0.001);
    }

}
