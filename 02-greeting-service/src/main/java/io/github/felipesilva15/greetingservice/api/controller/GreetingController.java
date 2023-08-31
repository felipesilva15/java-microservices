package io.github.felipesilva15.greetingservice.api.controller;

import io.github.felipesilva15.greetingservice.configuration.GreetingConfiguration;
import io.github.felipesilva15.greetingservice.domain.entity.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/greeting")
public class GreetingController {
    private static final String template = "%s, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private GreetingConfiguration config;

    @GetMapping()
    public ResponseEntity<Greeting> greeting(@RequestParam(value = "name", defaultValue = "") String name) {
        if (name.isEmpty()) {
            name = config.getDeafaultValue();
        }

        Greeting response = new Greeting(counter.incrementAndGet(), String.format(template, config.getGreeting(), name));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
