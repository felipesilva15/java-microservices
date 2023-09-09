package io.github.felipesilva15.bookservice.api.client;

import io.github.felipesilva15.bookservice.api.dto.CambioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cambio-service")
public interface CambioClient {
    @GetMapping("cambio-service/{amount}/{from}/{to}")
    public CambioDTO getCambio(@PathVariable Double amount, @PathVariable String from, @PathVariable String to);
}
