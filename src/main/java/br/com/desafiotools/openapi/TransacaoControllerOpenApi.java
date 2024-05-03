package br.com.desafiotools.openapi;

import br.com.desafiotools.dto.TransacaoCreateDTO;
import br.com.desafiotools.model.Transacao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Transacao")
public interface TransacaoControllerOpenApi {
    @Operation(summary = "Cria novas transações")
    ResponseEntity<Transacao> criarTransacao(@Valid @RequestBody TransacaoCreateDTO transacao);

    @Operation(summary = "Busca todas as transações existentes")
    ResponseEntity<List<Transacao>> buscarTransacao();

    @Operation(summary = "Busca transações por id")
    ResponseEntity<Transacao> buscarTransacaoPorId(@Valid @PathVariable Long id);
}
