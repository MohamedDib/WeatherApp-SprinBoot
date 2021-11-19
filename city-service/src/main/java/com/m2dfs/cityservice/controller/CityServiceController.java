package com.m2dfs.cityservice.controller;

import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class CityServiceController {
    @Autowired
    RestTemplate restTemplate;

    private String apiKey = "ZvGwrLnAs2GLYXzMrZrA7BlrIzC5jTkT";
    private String language = "fr-fr";

    @ApiOperation(value = "Get details of a city ", response = CityServiceController.class, tags = "getCity")
    @RequestMapping(value = "/citydetails/{city}", method = RequestMethod.GET)
    public String getWeather(@PathVariable String city) {

        String response = restTemplate.exchange(
                "http://dataservice.accuweather.com/locations/v1/cities/search?apikey="+apiKey+"&q={city}&language="+language,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {},
                city).getBody();


        return response;
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
