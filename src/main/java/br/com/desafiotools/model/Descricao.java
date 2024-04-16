package br.com.desafiotools.model;

import br.com.desafiotools.model.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
@Entity
public class Descricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "valor não deve ser nulo")
    @Positive(message = "valor deve ser positivo")
    private BigDecimal valor;

    @NotNull(message = "dataHora não deve ser nulo!")
    private LocalDateTime dataHora;

    @NotNull(message = "establecimento não deve ser nulo!")
    @NotBlank(message = "estabelcimento não deve estar em branco!")
    private String estabelecimento;

    @NotNull(message = "nsu não deve ser nulo!")
    @Positive(message = "nsu deve ser positivo!")
    @Size(min = 15, max = 15)
    private BigInteger nsu;

    @NotNull(message = "nsu não deve ser nulo!")
    @Positive(message = "nsu deve ser positivo!")
    @Size(min = 15, max = 15)
    private BigInteger codigoAutorizacao;

    @NotNull(message = "Status não deve ser nulo!")
    private Status status;
}
