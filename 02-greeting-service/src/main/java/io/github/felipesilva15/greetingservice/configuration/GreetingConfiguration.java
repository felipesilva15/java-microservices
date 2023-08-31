package io.github.felipesilva15.greetingservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
@ConfigurationProperties("greeting-service")
public class GreetingConfiguration {
    private String greeting;
    private String deafaultValue;

    public GreetingConfiguration() { }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getDeafaultValue() {
        return deafaultValue;
    }

    public void setDeafaultValue(String deafaultValue) {
        this.deafaultValue = deafaultValue;
    }
}
