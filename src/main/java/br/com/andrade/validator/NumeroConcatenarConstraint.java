package br.com.andrade.validator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Documented
@Constraint(validatedBy = NumeroConcatenarValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NumeroConcatenarConstraint {
	String message() default "Número invalido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
