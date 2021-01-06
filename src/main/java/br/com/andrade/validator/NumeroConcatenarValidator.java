package br.com.andrade.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class NumeroConcatenarValidator implements ConstraintValidator<NumeroConcatenarConstraint, Integer> {
	
	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		Boolean isValid = true;
		try {
			if(validarNumeroMaiorDezExpoenteDezMil(value)) {
				context.buildConstraintViolationWithTemplate("Número deve ser menor que 10^5").addConstraintViolation();
				isValid &= false;
			}
			if(value.compareTo(1) < 0) {
				context.buildConstraintViolationWithTemplate("Número deve ser maior ou igual a 1").addConstraintViolation();
				isValid &= false;
			}
			return isValid;
		}catch (NumberFormatException e) {
			return false;
		}
	}
	
	private Boolean validarNumeroMaiorDezExpoenteDezMil(Integer numero) {
		Integer numeroMaximo = Double.valueOf(Math.pow(Double.valueOf(10), Double.valueOf(5))).intValue();
		return numero.compareTo(numeroMaximo) > 0;
	}

}
