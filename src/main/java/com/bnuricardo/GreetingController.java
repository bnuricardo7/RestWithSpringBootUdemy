package com.bnuricardo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bnuricardo.exception.UnsuportedMathOperationException;

@RestController
public class GreetingController {

    @RequestMapping(value = "sum/{number1}/{number2}", method = RequestMethod.GET)
    public Double greeting(@PathVariable(value = "number1") String number1, @PathVariable(value = "number2") String number2) throws Exception {

	if (!isNumeric(number1) || !isNumeric(number2)) {
	    throw new UnsuportedMathOperationException("Insira um valor numérico.");
	}

	Double sum = convertToDouble(number1) + convertToDouble(number2);

	return sum;
    }

    private Double convertToDouble(String strNumber) {
	if (strNumber == null)
	    return 0d;
	strNumber = strNumber.replaceAll(",", ".");
	if (isNumeric(strNumber))
	    return Double.parseDouble(strNumber);
	return 0d;
    }

    private boolean isNumeric(String strNumber) {
	if (strNumber == null)
	    return false;
	String number = strNumber.replaceAll(",", ".");
	return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
