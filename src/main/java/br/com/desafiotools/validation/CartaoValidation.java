package br.com.desafiotools.validation;

import br.com.desafiotools.validation.constraints.Cartao;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CartaoValidation implements ConstraintValidator<Cartao, String> {
    @Override
    public void initialize(Cartao constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }

    /*private boolean numeroCartaoValido(String value) {
        String novaString = tiraEspacosEmBranco(value);
        if (novaString != null && 16 >= novaString.length() && novaString.length() >= 13) {
            for (int indice = 0; indice < value.length(); indice++) {

            }
        }
    }*/

    private String tiraEspacosEmBranco(String string) {
        // Troca . / e - por espaços em branco
        string = string.replaceAll("[-/\\.]", " ");

        String stringNova = "";
        for (int indice = 0; indice < string.length(); indice++) {
            if (string.charAt(indice) != ' ') {
                stringNova += string.charAt(indice);
            }
        }
        return stringNova;
    }

}
