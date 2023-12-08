package br.edu.ifms.instrumentos.musicais.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifms.instrumentos.musicais.dto.InstrumentoCreateDTO;
import br.edu.ifms.instrumentos.musicais.dto.InstrumentoResponseDTO;
import br.edu.ifms.instrumentos.musicais.model.Instrumento;

@Component
public class InstrumentoMapper {

    @Autowired
    private ModelMapper mapper;

    public Instrumento toEntity(InstrumentoCreateDTO dto) {
        Instrumento entity = mapper.map(dto, Instrumento.class);
        return entity;
    }

    public InstrumentoResponseDTO toDTO(Instrumento entity) {
        InstrumentoResponseDTO dto = mapper.map(entity, InstrumentoResponseDTO.class);
        return dto;
    }

    public List<InstrumentoResponseDTO> toDTO(List<Instrumento> instrumentos) {
        return instrumentos.stream().map(instrumento -> toDTO(instrumento)).collect(Collectors.toList());
    }
}
