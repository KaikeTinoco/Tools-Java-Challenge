package br.com.desafiotools.repositories;

import br.com.desafiotools.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    Optional<Transacao> findByCartao(String cartao);

}
