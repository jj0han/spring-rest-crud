package br.edu.ifms.instrumentos.musicais.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifms.instrumentos.musicais.exception.InstrumentoNotFoundException;
import br.edu.ifms.instrumentos.musicais.model.Instrumento;
import br.edu.ifms.instrumentos.musicais.repository.InstrumentoRepository;

@Service
public class InstrumentoService {
    @Autowired
    private InstrumentoRepository instrumentoRepository;

    public Instrumento save(Instrumento instrumento) {
        return instrumentoRepository.save(instrumento);
    }

    public List<Instrumento> findAll() {
        return instrumentoRepository.findAll();
    }

    public Instrumento findById(Long id) throws InstrumentoNotFoundException {
        Optional<Instrumento> opt = instrumentoRepository.findById(id);

        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    public Instrumento update(Long id, Instrumento instrumento) throws InstrumentoNotFoundException {
        Instrumento instrumentoGravado = findById(id);
        instrumentoGravado.setMarca(instrumento.getMarca());
        instrumentoGravado.setModelo(instrumento.getModelo());
        return instrumentoRepository.save(instrumentoGravado);
    }

    public void delete(Long id) throws InstrumentoNotFoundException {
        Instrumento instrumento = findById(id);
        instrumentoRepository.delete(instrumento);
    }
}
