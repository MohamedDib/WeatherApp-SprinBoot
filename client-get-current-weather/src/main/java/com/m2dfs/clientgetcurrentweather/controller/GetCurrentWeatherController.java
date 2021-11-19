package com.m2dfs.clientgetcurrentweather.controller;

import com.google.gson.Gson;
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

import java.util.Map;

@Api(value = "GetCurrentWeatherController", description = "Here we get the current weather data from other services endpoints by using the city name")
@RestController
public class GetCurrentWeatherController {

    @Autowired
    RestTemplate restTemplate;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @RequestMapping(value = "/getCurrentWeatherOfCity/{cityName}", method = RequestMethod.GET)
    public String getWeather(@PathVariable String cityName) {

        String cityCode = getCityCode(cityName);

        String response = restTemplate.exchange(
                "http://weather-service/currentWeather/"+cityCode,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {},
                cityName).getBody();
        return response;
    }

    public String getCityCode(String cityName) {
        String response = restTemplate.exchange(
                "http://localhost:8282/citydetails/{cityName}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {},
                cityName).getBody();

        // parse JSON and get only the code from city Infos
        String jsonRes = response;
        jsonRes = jsonRes.substring(1, jsonRes.length() - 1);
        Map jsonObj = new Gson().fromJson(jsonRes, Map.class);
        String cityCode = (String) jsonObj.get("Key");

        return cityCode;
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

