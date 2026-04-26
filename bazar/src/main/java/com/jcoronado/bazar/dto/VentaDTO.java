package com.jcoronado.bazar.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class VentaDTO {

    private Long codigoVenta;
    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fechaVenta;
    private Double total;
    @NotEmpty(message = "Debe haber al menos un producto en la venta")
    private List<@Valid DetalleVentaDTO> detalleVentaDTO;
    @NotNull(message = "El cliente es obligatorio")
    private Long clienteId;

    public VentaDTO() {
    }

    public VentaDTO(Long codigoVenta, LocalDate fechaVenta, Double total, List<DetalleVentaDTO> detalleVentaDTO, Long clienteId) {
        this.codigoVenta = codigoVenta;
        this.fechaVenta = fechaVenta;
        this.total = total;
        this.detalleVentaDTO = detalleVentaDTO;
        this.clienteId = clienteId;
    }
}

