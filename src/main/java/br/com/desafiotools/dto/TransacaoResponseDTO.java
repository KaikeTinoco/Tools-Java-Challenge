package br.com.desafiotools.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransacaoResponseDTO {
    private String cartao;
    private Long id;
    private DescricaoResponseDTO descricaoResponseDTO;
    private FormaPagamentoResponseDTO formaPagamentoResponseDTO;
}
