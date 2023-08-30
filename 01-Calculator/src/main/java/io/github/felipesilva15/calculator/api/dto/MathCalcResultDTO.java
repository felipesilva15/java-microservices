package io.github.felipesilva15.calculator.api.dto;

import io.github.felipesilva15.calculator.domain.enums.MathOperation;

public record MathCalcResultDTO(Double n1, Double n2, Double result, MathOperation operation) {
}