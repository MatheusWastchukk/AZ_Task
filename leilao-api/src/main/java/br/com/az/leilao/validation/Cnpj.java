package br.com.az.leilao.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = CnpjValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface Cnpj {

    String message() default "deve ser um CNPJ valido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
