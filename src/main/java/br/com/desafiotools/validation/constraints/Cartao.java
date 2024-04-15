package br.com.desafiotools.validation.constraints;

import br.com.desafiotools.validation.CartaoValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CartaoValidation.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Cartao {

    String message() default  "número de cartão inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
