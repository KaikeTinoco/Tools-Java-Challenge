package br.com.desafiotools.dto;

import br.com.desafiotools.model.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class DescricaoResponseDTO {
    private BigDecimal valor;
    private LocalDateTime dataHora;
    private String estabelecimento;
    private Integer[] nsu;
    private Integer[] codigoAtuorizacao;
    private Status status;
}
