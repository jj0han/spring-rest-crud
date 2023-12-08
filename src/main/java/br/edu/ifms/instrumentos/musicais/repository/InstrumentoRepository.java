package br.edu.ifms.instrumentos.musicais.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifms.instrumentos.musicais.model.Instrumento;

public interface InstrumentoRepository extends JpaRepository<Instrumento, Long> {

}
