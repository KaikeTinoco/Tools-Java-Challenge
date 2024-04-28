package br.com.desafiotools.resource;

import br.com.desafiotools.dto.TransacaoCreateDTO;
import br.com.desafiotools.dto.TransacaoResponseDTO;
import br.com.desafiotools.model.Pagamento;
import br.com.desafiotools.model.Transacao;
import br.com.desafiotools.service.TransacaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/transacao")
public class TransacaoController {
    @Autowired
    private TransacaoService transacaoService;


    @PostMapping(path = "/criar")
    public ResponseEntity<Transacao> criarTransacao(@Valid @RequestBody TransacaoCreateDTO transacao){
        return ResponseEntity.ok(transacaoService.criarPagamento(transacao));
    }

    @GetMapping(path = "/busca")
    public ResponseEntity<List<Transacao>> buscarTransacao(){
        return ResponseEntity.ok(transacaoService.buscarTransacao());
    }

    @GetMapping(path = "/buscaId/{id}")
    public ResponseEntity<Transacao> buscarTransacaoPorId(@Valid @PathVariable Long id){
        return ResponseEntity.ok(transacaoService.buscarTransacaoPorId(id));
    }
}
