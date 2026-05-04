package com.jcoronado.bazar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo_venta;
    @Column(name = "fecha_venta")
    private LocalDate fechaVenta;
    private Double total;
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<DetalleVenta> detalleVentas;
    @ManyToOne
    private Cliente unCliente;

    public Venta() {
    }

    public Venta(Long codigo_venta, LocalDate fechaVenta, Double total, List<DetalleVenta> detalleVentas, Cliente unCliente) {
        this.codigo_venta = codigo_venta;
        this.fechaVenta = fechaVenta;
        this.total = total;
        this.detalleVentas = detalleVentas;
        this.unCliente = unCliente;
    }

}
