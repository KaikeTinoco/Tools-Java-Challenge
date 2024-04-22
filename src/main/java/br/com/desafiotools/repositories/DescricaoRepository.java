package br.com.desafiotools.repositories;

import br.com.desafiotools.model.Descricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescricaoRepository extends JpaRepository<Descricao, Long> {
}
