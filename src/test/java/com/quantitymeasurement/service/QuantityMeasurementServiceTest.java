package com.quantitymeasurement.service;

import com.quantitymeasurement.enumeration.Measurement;
import com.quantitymeasurement.enumeration.Unit;
import com.quantitymeasurement.exception.QuantityMeasurementException;
import com.quantitymeasurement.model.QuantityMeasurement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static com.quantitymeasurement.enumeration.Measurement.LENGTH;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class QuantityMeasurementServiceTest {

    QuantityMeasurementService quantityMeasurementService;

    @BeforeEach
    void setUp() {
        quantityMeasurementService = new QuantityMeasurementService();
    }

    @Test
    public void given1Metre_whenConvertedToKilometre_shouldReturnConvertedResults() {
        double convertedValue = quantityMeasurementService.convertTo(new QuantityMeasurement(1.0, Unit.METRE),Unit.KILOMETRE);
        assertEquals(0.001,convertedValue,0.0);
    }

    @Test
    public void given1Inch_whenConvertedToYard_shouldReturnConvertedResults() {
        double convertedValue = quantityMeasurementService.convertTo(new QuantityMeasurement(1.0,Unit.INCH),Unit.YARD);
        assertEquals(0.0277778,convertedValue,0.00001);
    }

    @Test
    public void given1Feet_whenConvertedToInch_shouldReturnConvertedResults() {
        double convertedValue = quantityMeasurementService.convertTo(new QuantityMeasurement(1.0,Unit.FEET),Unit.INCH);
        assertEquals(12,convertedValue,0.00001);
    }

    @Test
    public void given1Centimetre_whenConvertedToMillimetre_shouldReturnConvertedResults() {
        double convertedValue = quantityMeasurementService.convertTo(new QuantityMeasurement(1.0,Unit.CENTIMETRE),Unit.MILLIMETRE);
        assertEquals(10,convertedValue,0.00001);
    }

    @Test
    public void given1Litre_whenConvertedToYard_shouldThrowException() {
        try {
            quantityMeasurementService.convertTo(new QuantityMeasurement(1.0, Unit.LITRE), Unit.YARD);
        } catch (QuantityMeasurementException e) {
            assertEquals("LITRE cannot convert to YARD", e.getMessage());
        }
    }

    @Test
    public void given1Litre_whenConvertedToGallon_shouldReturnConvertedResults() {
        double convertedValue = quantityMeasurementService.convertTo(new QuantityMeasurement(1.0,Unit.LITRE),Unit.GALLON);
        assertEquals(0.264172,convertedValue,0.00001);
    }

    @Test
    public void given1Litre_whenConvertedToMillilitre_shouldReturnConvertedResults() {
        double convertedValue = quantityMeasurementService.convertTo(new QuantityMeasurement(1.0,Unit.LITRE),Unit.MILLILITRE);
        assertEquals(1000.0,convertedValue,0.0);
    }

    @Test
    public void given1Kilogram_whenConvertedToTonne_shouldReturnConvertedResults() {
        double convertedValue = quantityMeasurementService.convertTo(new QuantityMeasurement(1.0,Unit.KILOGRAM),Unit.TONNE);
        assertEquals(0.001,convertedValue,0.0);
    }

    @Test
    public void given1Kilogram_whenConvertedToGram_shouldReturnConvertedResults() {
        double convertedValue = quantityMeasurementService.convertTo(new QuantityMeasurement(1.0,Unit.KILOGRAM),Unit.GRAM);
        assertEquals(1000,convertedValue,0.0);
    }

    @Test
    public void given1Kelvin_whenConvertedToFahrenheit_shouldReturnConvertedResults() {
        double convertedValue = quantityMeasurementService.convertTo(new QuantityMeasurement(1.0,Unit.KELVIN),Unit.FAHRENHEIT);
        assertEquals(-457.87,convertedValue,0.01);
    }

    @Test
    public void given1Kelvin_whenConvertedToCelcius_shouldReturnConvertedResults() {
        double convertedValue = quantityMeasurementService.convertTo(new QuantityMeasurement(1.0,Unit.KELVIN),Unit.CELCIUS);
        assertEquals(-272.15,convertedValue,0.01);
    }

    @Test
    public void given1Celcius_whenConvertedToFahrenheit_shouldReturnConvertedResults() {
        double convertedValue = quantityMeasurementService.convertTo(new QuantityMeasurement(1.0,Unit.CELCIUS),Unit.FAHRENHEIT);
        assertEquals(33.8,convertedValue,0.01);
    }

    @Test
    public void given1Celcius_whenConvertedToKelvin_shouldReturnConvertedResults() {
        double convertedValue = quantityMeasurementService.convertTo(new QuantityMeasurement(1.0,Unit.CELCIUS),Unit.KELVIN);
        assertEquals(274.15,convertedValue,0.01);
    }

    @Test
    public void given1Fahrenheit_whenConvertedToKelvin_shouldReturnConvertedResults() {
        double convertedValue = quantityMeasurementService.convertTo(new QuantityMeasurement(1.0,Unit.FAHRENHEIT),Unit.KELVIN);
        assertEquals(255.928,convertedValue,0.001);
    }

    @Test
    public void given1Fahrenheit_whenConvertedToCelcius_shouldReturnConvertedResults() {
        double convertedValue = quantityMeasurementService.convertTo(new QuantityMeasurement(1.0,Unit.FAHRENHEIT),Unit.CELCIUS);
        assertEquals(-17.2222,convertedValue,0.001);
    }

    @Test
    public void givenMassMeasurement_whenvalid_shouldReturnListOfMassUnits() {
        List<Unit> units = quantityMeasurementService.getUnits(Measurement.MASS);
        List<Unit> expectedUnits = new ArrayList<>();
        expectedUnits.add(Unit.GRAM);
        expectedUnits.add(Unit.KILOGRAM);
        expectedUnits.add(Unit.TONNE);
        assertEquals(expectedUnits,units);
    }

    @Test
    public void givenTemperatureMeasurement_whenvalid_shouldReturnListOfTemperatureUnits() {
        List<Unit> units = quantityMeasurementService.getUnits(Measurement.TEMPERATURE);
        List<Unit> expectedUnits = new ArrayList<>();
        expectedUnits.add(Unit.KELVIN);
        expectedUnits.add(Unit.CELCIUS);
        expectedUnits.add(Unit.FAHRENHEIT);
        assertEquals(expectedUnits,units);
    }

    @Test
    public void givenVolumeMeasurement_whenvalid_shouldReturnListOfVolumeUnits() {
        List<Unit> units = quantityMeasurementService.getUnits(Measurement.VOLUME);
        List<Unit> expectedUnits = new ArrayList<>();
        expectedUnits.add(Unit.LITRE);
        expectedUnits.add(Unit.GALLON);
        expectedUnits.add(Unit.MILLILITRE);
        assertEquals(expectedUnits,units);
    }

    @Test
    public void givenLengthMeasurement_whenvalid_shouldReturnListOfLengthUnits() {
        List<Unit> units = quantityMeasurementService.getUnits(LENGTH);
        List<Unit> expectedUnits = new ArrayList<>();
        expectedUnits.add(Unit.FEET);
        expectedUnits.add(Unit.INCH);
        expectedUnits.add(Unit.YARD);
        expectedUnits.add(Unit.CENTIMETRE);
        expectedUnits.add(Unit.METRE);
        expectedUnits.add(Unit.KILOMETRE);
        expectedUnits.add(Unit.MILLIMETRE);
        assertEquals(expectedUnits,units);
    }

}
