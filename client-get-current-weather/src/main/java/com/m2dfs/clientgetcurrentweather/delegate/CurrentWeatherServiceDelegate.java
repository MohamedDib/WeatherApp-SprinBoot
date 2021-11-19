package com.m2dfs.clientgetcurrentweather.delegate;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;

@Service
public class CurrentWeatherServiceDelegate {
    @Autowired
    RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "callStudentServiceAndGetData_Fallback")
    public String callCurrentWeatherServiceAndGetData(String cityName) {
        System.out.println("Getting current weather details for  " + cityName);

        String cityCode = getCityCode(cityName);

        String response = restTemplate.exchange(
                "http://localhost:8181/currentWeather/"+cityCode,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {},
                cityName).getBody();

        System.out.println("Response Received as " + response + " -  " + new Date());

        return "Circuit Breaker closed - City Name -  " + cityName + " : Current weather " + response + " -  " + new Date();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @SuppressWarnings("unused")
    private String callStudentServiceAndGetData_Fallback(String schoolname) {
        System.out.println("Current Weather Service is Down, Circuit Breaker is open");
        return "CIRCUIT BREAKER ENABLED! Current weather service not responding. Don't worry, the weather is always beautiful - " + new Date();
    }

    @SuppressWarnings("unused")
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

}
