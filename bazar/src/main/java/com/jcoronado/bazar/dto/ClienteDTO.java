package com.jcoronado.bazar.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClienteDTO {

    private Long idCliente;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String apellido;

    public ClienteDTO() {
    }

    public ClienteDTO(Long idCliente, String nombre, String apellido) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
    }

}
