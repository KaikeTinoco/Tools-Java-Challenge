package br.com.desafiotools.model;

import br.com.desafiotools.model.enums.Tipo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagamento {
    @NotNull(message = "tipo não deve ser nulo!")
    private Tipo tipo;

    @NotNull(message = "parcelas não deve ser nulo!")
    @Positive(message = "parcelas deve ser positivo!")
    private int parcelas;
}
