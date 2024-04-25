package br.com.desafiotools.resource;

import br.com.desafiotools.dto.DescricaoCreateDTO;
import br.com.desafiotools.dto.FormaPagamentoCreateDTO;
import br.com.desafiotools.dto.TransacaoCreateDTO;
import br.com.desafiotools.service.TransacaoService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/transacao")
public class TransacaoController {
    @Autowired
    private TransacaoService transacaoService;


    @PostMapping(path = "/criar")
    public ResponseEntity<?> criarTransacao(@RequestBody TransacaoCreateDTO transacao){
        return transacaoService.criarTransacao(transacao);
    }

    @GetMapping(path = "/busca")
    public ResponseEntity<?> buscarTransacao(){
        return transacaoService.buscarTransacao();
    }
}
