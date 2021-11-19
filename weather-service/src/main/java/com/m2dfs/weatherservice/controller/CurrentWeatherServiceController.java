package com.m2dfs.weatherservice.controller;

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

@RestController
public class CurrentWeatherServiceController {

    @Autowired
    RestTemplate restTemplate;

    private String apiKey = "ZvGwrLnAs2GLYXzMrZrA7BlrIzC5jTkT";
    private String language = "fr-fr";

    @ApiOperation(value = "Get today's weather", response = CurrentWeatherServiceController.class, tags = "getWeather")
    @RequestMapping(value = "/currentWeather/{key}", method = RequestMethod.GET)
    public String getWeather(@PathVariable String key) {

        String response = restTemplate.exchange(
                "http://dataservice.accuweather.com/currentconditions/v1/{key}?apikey="+apiKey+"&language="+language,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {},
                key).getBody();

        return response;
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
