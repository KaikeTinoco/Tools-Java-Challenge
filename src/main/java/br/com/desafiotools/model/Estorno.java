package br.com.desafiotools.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Estorno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "transacao não deve ser nulo!")
    @ManyToOne
    private Transacao transacao;

    public Estorno(Transacao transacao){
        this.transacao = transacao;
    }
}
