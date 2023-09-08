package io.github.felipesilva15.bookservice.api.dto;

public record CambioDTO(Long id, String from, String to, Double conversionFactor, Double convertedValue, String environment) {
}
