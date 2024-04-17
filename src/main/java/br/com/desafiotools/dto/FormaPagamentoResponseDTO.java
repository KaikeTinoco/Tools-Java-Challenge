package br.com.desafiotools.dto;

import br.com.desafiotools.model.enums.Tipo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoResponseDTO {
    private Tipo tipo;
    private int parcelas;
}
