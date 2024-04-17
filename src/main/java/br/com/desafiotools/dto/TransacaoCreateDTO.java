package br.com.desafiotools.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransacaoCreateDTO {
    private String cartao;
    private DescricaoCreateDTO descricaoCreateDTO;
    private TransacaoCreateDTO transacaoCreateDTO;
}
