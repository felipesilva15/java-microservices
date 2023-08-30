package io.github.felipesilva15.calculator.api.controller;

import io.github.felipesilva15.calculator.api.dto.MathCalcResultDTO;
import io.github.felipesilva15.calculator.domain.enums.MathOperation;
import io.github.felipesilva15.calculator.exception.UnsuportedMathOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {
    @GetMapping("/sum/{n1}/{n2}")
    public ResponseEntity<MathCalcResultDTO> sum(@PathVariable("n1") String n1, @PathVariable("n2") String n2) throws Exception {
        if (!isNumeric(n1) || !isNumeric(n2)) {
            throw new UnsuportedMathOperationException("Please, set a numeric value");
        }

        Double result = Double.parseDouble(n1) + Double.parseDouble(n2);
        MathCalcResultDTO response = new MathCalcResultDTO(Double.parseDouble(n1), Double.parseDouble(n2), result, MathOperation.SUM);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/subtraction/{n1}/{n2}")
    public ResponseEntity<MathCalcResultDTO> subtraction(@PathVariable("n1") String n1, @PathVariable("n2") String n2) throws Exception {
        if (!isNumeric(n1) || !isNumeric(n2)) {
            throw new UnsuportedMathOperationException("Please, set a numeric value");
        }

        Double result = Double.parseDouble(n1) - Double.parseDouble(n2);
        MathCalcResultDTO response = new MathCalcResultDTO(Double.parseDouble(n1), Double.parseDouble(n2), result, MathOperation.SUBTRACTION);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/multiplication/{n1}/{n2}")
    public ResponseEntity<MathCalcResultDTO> multiplication(@PathVariable("n1") String n1, @PathVariable("n2") String n2) throws Exception {
        if (!isNumeric(n1) || !isNumeric(n2)) {
            throw new UnsuportedMathOperationException("Please, set a numeric value");
        }

        Double result = Double.parseDouble(n1) * Double.parseDouble(n2);
        MathCalcResultDTO response = new MathCalcResultDTO(Double.parseDouble(n1), Double.parseDouble(n2), result, MathOperation.MULTIPLICATION);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/division/{n1}/{n2}")
    public ResponseEntity<MathCalcResultDTO> division(@PathVariable("n1") String n1, @PathVariable("n2") String n2) throws Exception {
        if (!isNumeric(n1) || !isNumeric(n2)) {
            throw new UnsuportedMathOperationException("Please, set a numeric value");
        }

        Double result = Double.parseDouble(n1) / Double.parseDouble(n2);
        MathCalcResultDTO response = new MathCalcResultDTO(Double.parseDouble(n1), Double.parseDouble(n2), result, MathOperation.DIVISION);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/squareroot/{n1}")
    public ResponseEntity<MathCalcResultDTO> squareroot(@PathVariable("n1") String n1) throws Exception {
        if (!isNumeric(n1)) {
            throw new UnsuportedMathOperationException("Please, set a numeric value");
        }

        Double result = (Double) Math.sqrt(Double.parseDouble(n1));
        MathCalcResultDTO response = new MathCalcResultDTO(Double.parseDouble(n1), 0D, result, MathOperation.SQUAREROOT);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/avarage/{n1}/{n2}")
    public ResponseEntity<MathCalcResultDTO> avarage(@PathVariable("n1") String n1, @PathVariable("n2") String n2) throws Exception {
        if (!isNumeric(n1) || !isNumeric(n2)) {
            throw new UnsuportedMathOperationException("Please, set a numeric value");
        }

        Double result = (Double.parseDouble(n1) + Double.parseDouble(n2)) / 2;
        MathCalcResultDTO response = new MathCalcResultDTO(Double.parseDouble(n1), Double.parseDouble(n2), result, MathOperation.AVARAGE);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private boolean isNumeric(String number) {
        if (number == null) {
            return false;
        }

        String formatedNumber = number.replaceAll(",", ".");

        return formatedNumber.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
