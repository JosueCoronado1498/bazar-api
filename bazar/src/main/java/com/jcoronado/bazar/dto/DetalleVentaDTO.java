package com.jcoronado.bazar.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DetalleVentaDTO {

    @NotNull(message = "El producto es obligatorio")
    private Long productoId;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "Debe ser al menos 1")
    private int cantidad;

    public DetalleVentaDTO() {
    }

    public DetalleVentaDTO(Long productoId, int cantidad) {
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

}
