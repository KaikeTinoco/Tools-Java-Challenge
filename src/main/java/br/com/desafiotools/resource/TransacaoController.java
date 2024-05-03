package br.com.desafiotools.resource;

import br.com.desafiotools.dto.TransacaoCreateDTO;
import br.com.desafiotools.model.Transacao;
import br.com.desafiotools.openapi.TransacaoControllerOpenApi;
import br.com.desafiotools.service.TransacaoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/transacao")
public class TransacaoController implements TransacaoControllerOpenApi {
    @Autowired
    private TransacaoService transacaoService;

    @ApiResponses(value = @ApiResponse(responseCode = "200",description = "criado com sucesso"))
    @PostMapping(path = "/criar")
    public ResponseEntity<Transacao> criarTransacao(@Valid @RequestBody TransacaoCreateDTO transacao){
        return ResponseEntity.ok(transacaoService.criarPagamento(transacao));
    }
    @ApiResponses(value = @ApiResponse(responseCode = "200",description = "busca realizada com sucesso"))
    @GetMapping(path = "/busca")
    public ResponseEntity<List<Transacao>> buscarTransacao(){
        return ResponseEntity.ok(transacaoService.buscarTransacao());
    }

    @ApiResponses(value = @ApiResponse(responseCode = "200",description = "busca realizada com sucesso"))
    @GetMapping(path = "/buscaId/{id}")
    public ResponseEntity<Transacao> buscarTransacaoPorId(@Valid @PathVariable Long id){
        return ResponseEntity.ok(transacaoService.buscarTransacaoPorId(id));
    }
}
