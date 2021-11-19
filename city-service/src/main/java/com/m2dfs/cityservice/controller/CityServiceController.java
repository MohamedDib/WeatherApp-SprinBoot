package com.m2dfs.cityservice.controller;

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

@Api(value = "CityServiceController", description = "Getting a city info, this will be useful later to get the city code")
@RestController
public class CityServiceController {
    @Autowired
    RestTemplate restTemplate;

    private String apiKey = "ZvGwrLnAs2GLYXzMrZrA7BlrIzC5jTkT";
    private String language = "fr-fr";

    @ApiOperation(value = "Get details of a city ", response = CityServiceController.class, tags = "getCity")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
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
