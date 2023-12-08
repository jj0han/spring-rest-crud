package br.edu.ifms.instrumentos.musicais.controller;

import org.springframework.web.bind.annotation.RestController;

import br.edu.ifms.instrumentos.musicais.dto.InstrumentoCreateDTO;
import br.edu.ifms.instrumentos.musicais.dto.InstrumentoResponseDTO;
import br.edu.ifms.instrumentos.musicais.dto.mapper.InstrumentoMapper;
import br.edu.ifms.instrumentos.musicais.exception.InstrumentoNotFoundException;
import br.edu.ifms.instrumentos.musicais.model.Instrumento;
import br.edu.ifms.instrumentos.musicais.service.InstrumentoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/instrumentos")
public class InstrumentoController {
    @Autowired
    private InstrumentoService instrumentoService;

    @Autowired
    private InstrumentoMapper instrumentoMapper;

    @PostMapping
    public ResponseEntity<InstrumentoResponseDTO> save(@RequestBody InstrumentoCreateDTO instrumentoCreateDTO) {
        Instrumento instrumento = instrumentoMapper.toEntity(instrumentoCreateDTO);
        Instrumento savedInstrumento = instrumentoService.save(instrumento);
        InstrumentoResponseDTO instrumentoResponseDTO = instrumentoMapper.toDTO(savedInstrumento);
        return ResponseEntity.status(HttpStatus.OK).body(instrumentoResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<InstrumentoResponseDTO>> findAll() {
        List<Instrumento> instrumentos = instrumentoService.findAll();
        List<InstrumentoResponseDTO> instrumentoResponseDTOs = instrumentoMapper.toDTO(instrumentos);
        return ResponseEntity.status(HttpStatus.OK).body(instrumentoResponseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        try {
            Instrumento InstrumentoGravado = instrumentoService.findById(id);
            InstrumentoResponseDTO instrumentoResponseDTO = instrumentoMapper.toDTO(InstrumentoGravado);
            return ResponseEntity.status(HttpStatus.OK).body(instrumentoResponseDTO);
        } catch (InstrumentoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id,
            @RequestBody InstrumentoCreateDTO InstrumentoCreateDTO) {
        try {
            Instrumento Instrumento = instrumentoMapper.toEntity(InstrumentoCreateDTO);
            Instrumento InstrumentoGravado = instrumentoService.update(id, Instrumento);
            InstrumentoResponseDTO instrumentoResponseDTO = instrumentoMapper.toDTO(InstrumentoGravado);
            return ResponseEntity.status(HttpStatus.OK).body(instrumentoResponseDTO);
        } catch (InstrumentoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
        try {
            instrumentoService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Removido com sucesso.");
        } catch (InstrumentoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
