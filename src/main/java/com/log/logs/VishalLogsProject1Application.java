package com.log.logs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

@SpringBootApplication
public class VishalLogsProject1Application {

	public static void main(String[] args) {
		SpringApplication.run(VishalLogsProject1Application.class, args);
	}
	
	@Bean
    public MeterRegistry meterRegistry() {
        return new SimpleMeterRegistry();
    }

}
