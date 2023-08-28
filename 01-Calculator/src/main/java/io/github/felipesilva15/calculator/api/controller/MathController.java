package io.github.felipesilva15.calculator.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {
    @GetMapping("/sum/{n1}/{n2}")
    public Double sum(@PathVariable("n1") String n1, @PathVariable("n2") String n2) throws Exception {
        if (!isNumeric(n1) || !isNumeric(n2)) {
            throw new Exception();
        }

        return Double.parseDouble(n1) + Double.parseDouble(n2);
    }


    private boolean isNumeric(String number) {
        if (number == null) {
            return false;
        }

        String formatedNumber = number.replaceAll(",", ".");

        return formatedNumber.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
