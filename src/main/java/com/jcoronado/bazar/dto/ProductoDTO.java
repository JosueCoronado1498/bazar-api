package com.jcoronado.bazar.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductoDTO {

    private Long codigoProducto;
    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;
    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    private Double precio;
    @Min(value = 0, message = "La cantidad no puede ser negativa")
    private Double cantidadDisponible;

    public ProductoDTO() {
    }

    public ProductoDTO(Long codigoProducto, String nombre, Double precio, Double cantidadDisponible) {
        this.codigoProducto = codigoProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
    }

}
