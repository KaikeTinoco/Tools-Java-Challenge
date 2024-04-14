package br.com.desafiotools.model;

import br.com.desafiotools.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Descricao {
    private BigDecimal valor;
    private LocalDateTime dataHora;
    private String estabelecimento;
    private BigInteger nsu;
    private BigInteger codigoAutorizacao;
    private Status status;
}
