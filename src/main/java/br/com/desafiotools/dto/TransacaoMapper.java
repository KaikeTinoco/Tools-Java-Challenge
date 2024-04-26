package br.com.desafiotools.dto;

import br.com.desafiotools.model.Transacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
@Setter
@Getter
public class TransacaoMapper {
    @Autowired
    private ModelMapper mapper;
    private DescricaoMapper descricaoMapper;
    private FormaPagamentoMapper formaPagamentoMapper;

    public Transacao toTransacao(TransacaoCreateDTO transacaoCreateDTO){
        Transacao novaTransacao = mapper.map(transacaoCreateDTO, Transacao.class);
        return novaTransacao;
    }

    public TransacaoResponseDTO toDto(Transacao transacao){
        TransacaoResponseDTO dto = mapper.map(transacao, TransacaoResponseDTO.class);
        dto.setDescricaoResponseDTO(descricaoMapper.toDescricaoResponseDTO(transacao.getDescricao()));
        dto.setFormaPagamentoResponseDTO(formaPagamentoMapper.toDto(transacao.getFormaPagamento()));
        return dto;
    }

    public List<TransacaoResponseDTO> toDtoList(List<Transacao> transacaos){
        return transacaos.stream()
                .map(transacao -> toDto(transacao)).collect(Collectors.toList());
    }
}
