package com.quantitymeasurement.controller;

import com.google.gson.Gson;
import com.quantitymeasurement.enumeration.Unit;
import com.quantitymeasurement.exception.QuantityMeasurementException;
import com.quantitymeasurement.model.QuantityMeasurement;
import com.quantitymeasurement.service.QuantityMeasurementService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static com.quantitymeasurement.enumeration.Unit.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = QuantityMeasurementController.class)
public class QuantityMeasurementControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    QuantityMeasurementService quantityMeasurementService;

    @Autowired
    Gson gson;

    @Test
    void givenUrlForGetRequest_whenInvalid_shouldReturnStatus() {
        try {
            RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/quantity-measurement/")
                                                                  .accept(MediaType.APPLICATION_JSON)
                                                                  .contentType(MediaType.APPLICATION_JSON);
            mockMvc.perform(requestBuilder)
                   .andExpect(MockMvcResultMatchers.status().isNotFound());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void given1000MillilitreToConvert_whenConvertedToMetre_shouldReturn1() {
        try {
            when(quantityMeasurementService.convertTo(any(QuantityMeasurement.class), any())).thenReturn(1.0);
            RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/quantity-measurement/MILLIMETRE/1000/METRE")
                                                                  .accept(MediaType.APPLICATION_JSON)
                                                                  .contentType(MediaType.APPLICATION_JSON);
            MvcResult result = mockMvc.perform(requestBuilder)
                                      .andReturn();
            assertEquals("fail",HttpStatus.OK.value(),result.getResponse().getStatus());
            JSONAssert.assertEquals(gson.toJson(new QuantityMeasurement(1.0,METRE)),result.getResponse().getContentAsString(),false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void given1000MillilitreToConvert_whenConvertedToInvalidUnit_shouldReturnBadRequest() {
        try {
            when(quantityMeasurementService.convertTo(any(QuantityMeasurement.class), any())).thenReturn(1.0);
            RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/quantity-measurement/metr/1/metre")
                                                                  .accept(MediaType.APPLICATION_JSON)
                                                                  .contentType(MediaType.APPLICATION_JSON);
            mockMvc.perform(requestBuilder)
                   .andExpect(MockMvcResultMatchers.status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void given1000MillilitreToConvert_whenConvertedToInvalidUnitLitre_shouldReturnBadRequest() {
        try {
            when(quantityMeasurementService.convertTo(any(QuantityMeasurement.class), any()))
                .thenThrow(new QuantityMeasurementException("Invalid conversion"));
            RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/quantity-measurement/litre/1/metre")
                                                                  .accept(MediaType.APPLICATION_JSON)
                                                                  .contentType(MediaType.APPLICATION_JSON);
            mockMvc.perform(requestBuilder)
                   .andExpect(MockMvcResultMatchers.status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenUrlToGetListOfUnits_whenTemperatureMeasurementtype_shouldReturnStatusOk() {
        try {
            List<Unit> expectedUnits = new ArrayList<>();
            expectedUnits.add(Unit.KELVIN);
            when(quantityMeasurementService.getUnits(any())).thenReturn(expectedUnits);
            RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/quantity-measurement/temperature")
                                                                  .accept(MediaType.APPLICATION_JSON)
                                                                  .contentType(MediaType.APPLICATION_JSON);
            mockMvc.perform(requestBuilder)
                   .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenUrlToGetListOfUnits_whenInvalidMeasurementtype_shouldReturnStatusAsBadRequest() {
        try {
            List<Unit> expectedUnits = new ArrayList<>();
            expectedUnits.add(Unit.KELVIN);
            when(quantityMeasurementService.getUnits(any())).thenReturn(expectedUnits);
            RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/quantity-measurement/temperatur")
                                                                  .accept(MediaType.APPLICATION_JSON)
                                                                  .contentType(MediaType.APPLICATION_JSON);
            mockMvc.perform(requestBuilder)
                   .andExpect(MockMvcResultMatchers.status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}