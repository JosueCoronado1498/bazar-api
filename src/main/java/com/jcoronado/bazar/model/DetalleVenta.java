package com.jcoronado.bazar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cantidad;
    private Double precioUnitario;
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;
    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

    public DetalleVenta() {
    }

    public DetalleVenta(Long id, int cantidad, Double precioUnitario, Producto producto, Venta venta) {
        this.id = id;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.producto = producto;
        this.venta = venta;
    }

}
