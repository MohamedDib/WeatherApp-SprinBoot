package com.m2dfs.clientgetcurrentweather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableCircuitBreaker

public class ClientGetCurrentWeatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientGetCurrentWeatherApplication.class, args);
	}

}
