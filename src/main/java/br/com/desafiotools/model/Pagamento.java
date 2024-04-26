package br.com.desafiotools.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "transação não deve ser nulo!")
    @ManyToOne
    private Transacao transacao;

    public Pagamento(Transacao transacao){
        this.transacao = transacao;
    }
}
