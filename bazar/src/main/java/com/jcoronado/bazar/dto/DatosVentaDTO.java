package com.jcoronado.bazar.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class DatosVentaDTO {

    private Double montoTotal;
    private int cantidadVentas;

    public DatosVentaDTO() {
    }

    public DatosVentaDTO(Double montoTotal, int cantidadVentas) {
        this.montoTotal = montoTotal;
        this.cantidadVentas = cantidadVentas;
    }

}
