package com.quantitymeasurement;

import com.quantitymeasurement.model.Response;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QuantitymeasurementApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuantitymeasurementApplication.class, args);
    }

    @Bean
    public Response getResponse() {
        return new Response();
    }

}
