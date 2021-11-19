package com.m2dfs.weatherservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Api(value = "CurrentWeatherServiceController", description = "Getting current weather info from AccuWeather of specefic city")
@RestController
public class CurrentWeatherServiceController {

    @Autowired
    RestTemplate restTemplate;

    private String apiKey = "ZvGwrLnAs2GLYXzMrZrA7BlrIzC5jTkT";
    private String language = "fr-fr";

    @ApiOperation(value = "Get today's weather", response = CurrentWeatherServiceController.class, tags = "getWeather")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
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
