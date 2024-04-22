package br.com.desafiotools.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransacaoCreateDTO {
    private String cartao;
    private DescricaoCreateDTO descricaoCreateDTO;
    private FormaPagamentoCreateDTO formaPagamentoCreateDTO;
}
