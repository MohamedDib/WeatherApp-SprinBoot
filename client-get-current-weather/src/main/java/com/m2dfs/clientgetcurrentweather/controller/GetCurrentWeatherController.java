package com.m2dfs.clientgetcurrentweather.controller;

import com.m2dfs.clientgetcurrentweather.delegate.CurrentWeatherServiceDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetCurrentWeatherController {

    @Autowired
    CurrentWeatherServiceDelegate currentWeatherServiceDelegate;

    @RequestMapping(value = "/getCurrentWeatherOfCity/{cityName}", method = RequestMethod.GET)
    public String getWeather(@PathVariable String cityName) {

        System.out.println("Calling current weather service to get Data ...");
        //return currentWeatherServiceDelegate.callCurrentWeatherServiceAndGetData(cityName);
        return currentWeatherServiceDelegate.callCurrentWeatherServiceAndGetData(cityName);
    }

}

