package br.com.desafiotools.repositories;

import br.com.desafiotools.model.Estorno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstornoRepository extends JpaRepository<Estorno, Long> {
}
