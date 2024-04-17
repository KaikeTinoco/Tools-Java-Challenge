package br.com.desafiotools.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class DescricaoCreateDTO {
    private BigDecimal valor;
    private String dataHora;
    private String estabelecimento;
}
