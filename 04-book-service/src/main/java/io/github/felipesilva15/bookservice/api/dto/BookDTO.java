package io.github.felipesilva15.bookservice.api.dto;

import java.util.Date;

public record BookDTO(Long id, String author, Date launchDate, Double price, String title, String currency, String environment) {
}
