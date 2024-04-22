package br.com.desafiotools.dto;

import br.com.desafiotools.model.FormaPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
@Component
public class FormaPagamentoMapper {
    @Autowired
    private ModelMapper mapper;

    public FormaPagamento toFormaPagamento(FormaPagamentoCreateDTO dto){
        FormaPagamento novoFormaPagamento = mapper.map(dto, FormaPagamento.class);
        return novoFormaPagamento;
    }

    public FormaPagamentoResponseDTO toDto(FormaPagamento formaPagamento){
        FormaPagamentoResponseDTO dto = mapper.map(formaPagamento, FormaPagamentoResponseDTO.class);
        return dto;
    }

    public List<FormaPagamentoResponseDTO> toDtoList(List<FormaPagamento> formaPagamentos){
        return formaPagamentos.stream()
                .map(formaPagamento -> toDto(formaPagamento)).collect(Collectors.toList());
    }

}
