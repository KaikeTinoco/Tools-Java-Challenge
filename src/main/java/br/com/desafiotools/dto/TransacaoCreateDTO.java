package br.com.desafiotools.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransacaoCreateDTO {
    private DescricaoCreateDTO descricaoCreateDTO;
    private FormaPagamentoCreateDTO formaPagamentoCreateDTO;
    private String cartao;
}
