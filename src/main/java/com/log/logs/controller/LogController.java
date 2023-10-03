package com.log.logs.controller;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.log.logs.service.LogGenerator;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class LogController {

    private final MeterRegistry meterRegistry;  // MeterRegistry for managing metrics
    private final LogGenerator logGenerator;     // Service for log generation

    private final Counter totalTransactionCounter;  // Metric: Counts total transactions
    private final AtomicInteger activeClientCount = new AtomicInteger(0); // Metric: Tracks active clients
    private final Timer responseTimeTimer;  // Metric: Measures API call response time
    
    private final Counter httpErrorCounter;  // Metric: Counts HTTP errors

    @Autowired
    public LogController(MeterRegistry meterRegistry, LogGenerator logGenerator) {
        this.meterRegistry = meterRegistry;
        this.logGenerator = logGenerator;

        // Create and register a Counter metric for HTTP errors
        httpErrorCounter = Counter.builder("HTTP 500 Error counter")
                .description("HTTP Error Count")
                .register(meterRegistry);

        
        // Create and register a Counter metric for total transaction counts
        totalTransactionCounter = Counter.builder("total_transaction_counter")
                .description("Total Transaction Count")
                .register(meterRegistry);

        // Create and register a Timer metric for measuring API call response times
        responseTimeTimer = Timer.builder("api_call_response_time")
                .description("API Call Response Time")
                .register(meterRegistry);

        // Register the activeClientCountGauge as a gauge metric for tracking active client count
        Gauge.builder("active_client_gauge", activeClientCount, AtomicInteger::get)
                .description("Currently Active Client Count")
                .register(meterRegistry);
    }

    @SuppressWarnings("static-access")
    @GetMapping("/logs")
    @Timed(value = "api_call_response_time", description = "API Call Response Time")
    public Boolean getLogs(@RequestParam("action") String userAction,
                           @RequestParam("iteration") String iterationsStr,
                           @RequestParam("product") String product) {
        Timer.Sample sample = Timer.start(meterRegistry);  // Start measuring API call response time

        try {
            String arr []= new String[3];
            arr[0] = userAction;
            arr[1] = iterationsStr;
            arr[2] = product;
            logGenerator.main(arr);  // Generate logs
            
         // Check if the iteration value is more than 20
            if (iterationsStr != null && !iterationsStr.isEmpty()) {
                int iterations = Integer.parseInt(iterationsStr);
                if (iterations > 20) {
                    // Increment the HTTP error counter
                    httpErrorCounter.increment();
                    
                    // Simulate a 500 Internal Server Error
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Too many iterations");
                }
            }
            
        } finally {
            sample.stop(responseTimeTimer);  // Stop measuring API call response time
        }

        // Increment the total transaction counter
        totalTransactionCounter.increment();

        // Update the active client count (e.g., when a new client connects)
        activeClientCount.incrementAndGet();

        return true;
    }
    
}
