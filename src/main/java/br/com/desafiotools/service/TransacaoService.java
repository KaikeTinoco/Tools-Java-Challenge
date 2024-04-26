package br.com.desafiotools.service;

import br.com.desafiotools.dto.DescricaoMapper;
import br.com.desafiotools.dto.FormaPagamentoMapper;
import br.com.desafiotools.dto.TransacaoCreateDTO;
import br.com.desafiotools.dto.TransacaoMapper;
import br.com.desafiotools.model.*;
import br.com.desafiotools.model.enums.Status;
import br.com.desafiotools.repositories.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Random;


@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private  TransacaoMapper mapper;
    private DescricaoMapper descricaoMapper;
    private FormaPagamentoMapper formaPagamentoMapper;
    private final FormaPagamentoRepository formaPagamentoRepository;
    private final DescricaoRepository descricaoRepository;
    private final PagamentoRepository pagamentoRepository;
    private final EstornoRepository estornoRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public TransacaoService(TransacaoRepository transacaoRepository,
                            FormaPagamentoRepository formaPagamentoRepository,
                            DescricaoRepository descricaoRepository,
                            PagamentoRepository pagamentoRepository,
                            EstornoRepository estornoRepository,
                            TransacaoMapper mapper,
                            DescricaoMapper descricaoMapper,
                            FormaPagamentoMapper formaPagamentoMapper) {
        this.transacaoRepository = transacaoRepository;
        this.formaPagamentoRepository = formaPagamentoRepository;
        this.descricaoRepository = descricaoRepository;
        this.pagamentoRepository = pagamentoRepository;
        this.estornoRepository = estornoRepository;
        this.mapper = mapper;
        this.descricaoMapper = descricaoMapper;
        this.formaPagamentoMapper = formaPagamentoMapper;
    }


    public ResponseEntity<?> criarPagamento(TransacaoCreateDTO dto) {
        Transacao teste = mapper.toTransacao(dto);
        if(transacaoRepository.findByDescricaoDataHora(teste.getDescricao().getDataHora()).isEmpty()){
            gerarNsuECodigoAutorizacao(dto);
            Descricao descricao = gerarNsuECodigoAutorizacao(dto);
            descricaoRepository.save(descricao);
            FormaPagamento formaPagamento = formaPagamentoMapper.toFormaPagamento(dto.getFormaPagamentoCreateDTO());
            formaPagamentoRepository.save(formaPagamento);
            Transacao novaTransacao = new Transacao(dto.getCartao(),descricao, formaPagamento);
            transacaoRepository.save(novaTransacao);
            if (novaTransacao.getDescricao().getStatus() == Status.APROVADO){
                Pagamento pagamento = new Pagamento(novaTransacao);
                pagamentoRepository.save(pagamento);
                return ResponseEntity.status(201).body(pagamento);
            } else {
                Estorno estorno = new Estorno(novaTransacao);
                estornoRepository.save(estorno);
                return ResponseEntity.status(201).body(estorno);
            }
        } else {
            return ResponseEntity.badRequest().
                    body("Por favor, verifique se os dados foram corretamente informados");


        }
    }


    public ResponseEntity<?> buscarTransacao() {
        return ResponseEntity.ok(transacaoRepository.findAll());
    }

    private Descricao gerarNsuECodigoAutorizacao(TransacaoCreateDTO transacao){
        Random random = new Random();
        String nsu = "";
        String codigoAutorizacao = "";
        for (int i = 0; i < 15; i++){
            nsu += random.nextInt(9);
            codigoAutorizacao += random.nextInt(9);
        }
        Descricao descricao = descricaoMapper.toDescricao(transacao.getDescricaoCreateDTO());
        descricao.setCodigoAutorizacao(codigoAutorizacao);
        descricao.setNsu(nsu);
        decidirStatus(descricao);
        descricaoRepository.save(descricao);
        return descricao;
    }


    public ResponseEntity<?> buscarTransacaoPorId(Long id) {
        Transacao transacao = transacaoRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("por favor informe um id valido"));
        return ResponseEntity.ok(transacao);
    }


    //o método abaixo é apenas para teste da classe Status (tipo enum)
    private void decidirStatus(Descricao descricao){
        Random random = new Random();
        int c = random.nextInt(2);
        if (c == 1){
            descricao.setStatus(Status.APROVADO);
        } else {
            descricao.setStatus(Status.RECUSADO);
        }
    }

}
