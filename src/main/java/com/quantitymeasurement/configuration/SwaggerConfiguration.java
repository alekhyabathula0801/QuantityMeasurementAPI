package com.quantitymeasurement.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket productAPI() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                                                      .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                                                      .build()
                                                      .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        return new ApiInfo(
                "Quantity Measurement API",
                "Convert the given quantity to required unit",
                "1.0", "urn:tos",
                new Contact("Alekhya","","alekhya@gmail.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>()
        );
    }

}
