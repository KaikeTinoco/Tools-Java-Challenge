package br.com.desafiotools.service;

import br.com.desafiotools.dto.*;
import br.com.desafiotools.model.*;
import br.com.desafiotools.model.enums.Status;
import br.com.desafiotools.model.enums.Tipo;
import br.com.desafiotools.repositories.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
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


    public Transacao criarPagamento(TransacaoCreateDTO dto) {
        Transacao teste = mapper.toTransacao(dto);
        if(transacaoRepository.findByDescricaoDataHora(teste.getDescricao().getDataHora()).isEmpty()){
            gerarNsuECodigoAutorizacao(dto);
            Descricao descricao = gerarNsuECodigoAutorizacao(dto);
            descricaoRepository.save(descricao);
            FormaPagamento formaPagamento = validarFormaPagamento(dto);
            Transacao novaTransacao = new Transacao(dto.getCartao(),descricao, formaPagamento);
            transacaoRepository.save(novaTransacao);
            if (novaTransacao.getDescricao().getStatus() == Status.APROVADO){
                Pagamento pagamento = new Pagamento(novaTransacao);
                pagamentoRepository.save(pagamento);
                return novaTransacao;
            } else {
                Estorno estorno = new Estorno(novaTransacao);
                estornoRepository.save(estorno);
                return novaTransacao;
            }
        } else {
            throw new IllegalStateException("já existe uma transação com esses dados!");
        }
    }


    public List<Transacao> buscarTransacao() {
        return transacaoRepository.findAll();
    }

    private Descricao gerarNsuECodigoAutorizacao(TransacaoCreateDTO transacao){
        Random random = new Random();
        String nsu = "";
        String codigoAutorizacao = "";
        for (int i = 0; i < 15; i++){
            nsu += random.nextInt(9);
            codigoAutorizacao += random.nextInt(9);
        }
        return getDescricao(transacao, codigoAutorizacao, nsu);
    }

    private Descricao getDescricao(TransacaoCreateDTO transacao, String codigoAutorizacao, String nsu) {
        Descricao descricao = descricaoMapper.toDescricao(transacao.getDescricaoCreateDTO());
        descricao.setCodigoAutorizacao(codigoAutorizacao);
        descricao.setNsu(nsu);
        decidirStatus(descricao);
        descricaoRepository.save(descricao);
        return descricao;
    }


    public Transacao buscarTransacaoPorId(Long id) {
        Transacao transacao = transacaoRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("por favor informe um id valido"));
        return transacao;
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

    private FormaPagamento validarFormaPagamento(TransacaoCreateDTO dto){
        Tipo tipo = dto.getFormaPagamentoCreateDTO().getTipo();
        if(tipo == Tipo.AVISTA && dto.getFormaPagamentoCreateDTO().getParcelas() > 1){
            throw new IllegalStateException("O número de parcelas não pode ser maior que um para pagamentos avista!");
        } else {
            FormaPagamento formaPagamento = formaPagamentoMapper.toFormaPagamento(dto.getFormaPagamentoCreateDTO());
            formaPagamentoRepository.save(formaPagamento);
            return formaPagamento;
        }
    }

}
