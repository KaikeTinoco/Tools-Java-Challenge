package br.com.desafiotools.dto;

import br.com.desafiotools.model.Descricao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Setter
@Getter
@Component
public class DescricaoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Descricao toDescricao (DescricaoCreateDTO descricaoCreateDTO){
        Descricao descricaoNova = modelMapper.map(descricaoCreateDTO, Descricao.class);
        return descricaoNova;
    }

    public DescricaoResponseDTO toDescricaoResponseDTO(Descricao descricao){
        DescricaoResponseDTO dto = modelMapper.map(descricao, DescricaoResponseDTO.class);
        return dto;
    }

    public List<DescricaoResponseDTO> toDtoList(List<Descricao> descricaos){
        return descricaos.stream()
                .map(descricao -> toDescricaoResponseDTO(descricao)).collect(Collectors.toList());
    }
}
