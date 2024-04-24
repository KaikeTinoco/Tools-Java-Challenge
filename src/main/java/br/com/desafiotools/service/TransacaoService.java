package br.com.desafiotools.service;

import br.com.desafiotools.dto.*;
import br.com.desafiotools.model.Descricao;
import br.com.desafiotools.model.FormaPagamento;
import br.com.desafiotools.model.Transacao;
import br.com.desafiotools.model.enums.Status;
import br.com.desafiotools.repositories.DescricaoRepository;
import br.com.desafiotools.repositories.FormaPagamentoRepository;
import br.com.desafiotools.repositories.TransacaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import kong.unirest.JsonNode;
import kong.unirest.JsonResponse;
import kong.unirest.Unirest;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Random;

@AllArgsConstructor
@Service
public class TransacaoService {
    @Autowired
    private TransacaoRepository transacaoRepository;
    private TransacaoMapper mapper;
    private DescricaoMapper descricaoMapper;
    private FormaPagamentoMapper formaPagamentoMapper;
    private FormaPagamentoRepository formaPagamentoRepository;
    private DescricaoRepository descricaoRepository;
    private ObjectMapper objectMapper;



    @SneakyThrows
    public ResponseEntity<?> criarTransacao(String json) {
        TransacaoCreateDTO dto = objectMapper.readValue(json, TransacaoCreateDTO.class);
        if(transacaoRepository.findByCartao(dto.getCartao()).isEmpty()){
            Descricao descricao = descricaoMapper.toDescricao(dto.getDescricaoCreateDTO());
            descricaoRepository.save(descricao);
            FormaPagamento formaPagamento = formaPagamentoMapper.toFormaPagamento(dto.getFormaPagamentoCreateDTO());
            formaPagamentoRepository.save(formaPagamento);
            Transacao novaTransacao = mapper.toTransacao(dto);
            gerarNsuECodigoAutorizacao(novaTransacao);
            Descricao descricao1 = novaTransacao.getDescricao();
            descricao1.setStatus(Status.APROVADO);
            transacaoRepository.save(novaTransacao);
            return ResponseEntity.status(201).body("");
        } else {
            return ResponseEntity.badRequest().
                    body("Por favor, verifique se os dados foram corretamente informados");
        }
    }


    public ResponseEntity<?> buscarTransacao() {
        return ResponseEntity.ok(transacaoRepository.findAll());
    }

    private void gerarNsuECodigoAutorizacao(Transacao transacao){
        Random random = new Random();
        Integer[] nsu = new Integer[15];
        Integer[] codigoAutorizacao = new Integer[15];
        for (int i = 0; i < 15; i++){
            nsu[i] = random.nextInt(9);
            codigoAutorizacao[i] = random.nextInt(9);
        }
        Descricao descricao = transacao.getDescricao();
        descricao.setNsu(nsu);
        descricao.setCodigoAutorizacao(codigoAutorizacao);

    }


}
