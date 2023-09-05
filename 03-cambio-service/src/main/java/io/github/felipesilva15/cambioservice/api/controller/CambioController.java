package io.github.felipesilva15.cambioservice.api.controller;

import io.github.felipesilva15.cambioservice.domain.entity.Cambio;
import io.github.felipesilva15.cambioservice.domain.repository.CambioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
@RequestMapping("cambio-service")
public class CambioController {
    @Autowired
    private Environment environment;

    @Autowired
    private CambioRepository repository;

    @GetMapping("/{amount}/{from}/{to}")
    public ResponseEntity<Cambio> getCambio(@PathVariable("amount") BigDecimal amount, @PathVariable("from") String from, @PathVariable("to") String to) {
        Cambio cambio = repository.findByFromAndTo(from, to);

        if (cambio == null) {
            throw new RuntimeException("Currency Unsupported");
        }

        var port = environment.getProperty("local.server.port");
        BigDecimal convertedValue = cambio.getConvertionFactor().multiply(amount);

        cambio.setEnvironment(port);
        cambio.setConvertedValue(convertedValue.setScale(2, RoundingMode.CEILING));

        return new ResponseEntity<>(cambio, HttpStatus.OK);
    }
}
