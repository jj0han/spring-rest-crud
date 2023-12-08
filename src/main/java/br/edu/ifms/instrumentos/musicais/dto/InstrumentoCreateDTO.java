package br.edu.ifms.instrumentos.musicais.dto;

import jakarta.validation.constraints.NotBlank;

public class InstrumentoCreateDTO {
    @NotBlank(message = "O modelo deve ser informado")
    private String modelo;

    @NotBlank(message = "A marca deve ser informada")
    private String marca;

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
