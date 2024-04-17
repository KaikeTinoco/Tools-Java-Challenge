package br.com.desafiotools.dto;

import br.com.desafiotools.model.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DescricaoResponseDTO {
    private BigDecimal valor;
    private String dataHora;
    private String estabelecimento;
    private String nsu;
    private String codigoAtuorizacao;
    private Status status;
}
