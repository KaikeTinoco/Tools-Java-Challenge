package br.com.desafiotools.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.validator.constraints.LuhnCheck;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @LuhnCheck
    @NotNull(message = "cartão não deve ser nulo!")
    @NotBlank(message = "cartão não deve estar em branco!")
    private String cartao;

    @NotNull(message = "descricao não deve ser nulo!")
    @ManyToOne
    private Descricao descricao;

    @NotNull(message = "formaPagamento não deve ser nulo!")
    @ManyToOne
    private FormaPagamento formaPagamento;


    public Transacao(String cartao, Descricao descricao, FormaPagamento formaPagamento) {
        this.cartao = cartao;
        this.descricao = descricao;
        this.formaPagamento = formaPagamento;
    }
}

