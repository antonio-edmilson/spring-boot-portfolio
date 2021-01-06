package br.com.andrade.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DigitoUnicoValidator implements ConstraintValidator<DigitoUnicoConstraint, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Boolean isValid = true;
		try {
			Integer numero = Integer.parseInt(value);
			if(validarNumeroMaiorDezExpoenteDezMil(numero)) {
				context.buildConstraintViolationWithTemplate("Número deve ser menor que 10^1000000")
				.addConstraintViolation();
				isValid &= false;
			}
			if(numero.compareTo(1) < 0) {
				context.buildConstraintViolationWithTemplate("Número deve ser maior ou igual a 1").addConstraintViolation();
				isValid &= false;
			}
			return isValid;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private Boolean validarNumeroMaiorDezExpoenteDezMil(Integer numero) {
		Integer numeroMaximo = Double.valueOf(Math.pow(Double.valueOf(10), Double.valueOf(1000000))).intValue();
		return numero.compareTo(numeroMaximo) > 0;	
	}

}
