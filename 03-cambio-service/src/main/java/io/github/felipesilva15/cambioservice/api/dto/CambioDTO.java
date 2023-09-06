package io.github.felipesilva15.cambioservice.api.dto;

import java.math.BigDecimal;

public record CambioDTO(Long id, String from, String to, BigDecimal conversionFactor, BigDecimal convertedValue, String environment) {
}
