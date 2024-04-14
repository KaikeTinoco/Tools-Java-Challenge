package br.com.desafiotools.model;

import br.com.desafiotools.model.enums.Tipo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagamento {
    private Tipo tipo;
    private int parcelas;
}
