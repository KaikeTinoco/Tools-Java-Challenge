package br.com.desafiotools.dto;

import br.com.desafiotools.model.enums.Tipo;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoCreateDTO {
    private Tipo tipo;

    @Nullable
    private int parcelas;


}
