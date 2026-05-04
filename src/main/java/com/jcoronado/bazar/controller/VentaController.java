package com.jcoronado.bazar.controller;

import com.jcoronado.bazar.dto.DatosVentaDTO;
import com.jcoronado.bazar.dto.DatosVentaMayorDTO;
import com.jcoronado.bazar.dto.ProductoDTO;
import com.jcoronado.bazar.dto.VentaDTO;
import com.jcoronado.bazar.service.IVentasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private IVentasService ventasService;


    @PostMapping("/crear")
    public ResponseEntity<VentaDTO> saveVenta(@Valid @RequestBody VentaDTO ventaDTO) {
        VentaDTO ventaCreada = ventasService.saveVenta(ventaDTO);

        return ResponseEntity.status(201).body(ventaCreada);
    }

    @GetMapping()
    public ResponseEntity<List<VentaDTO>> getVentas() {
        return ResponseEntity.ok(ventasService.getVentas());
    }

    @GetMapping("/{codigo_venta}")
    public ResponseEntity<VentaDTO> getVentaById(@PathVariable Long codigo_venta) {
        return ResponseEntity.ok(ventasService.getVentaById(codigo_venta));
    }

    @DeleteMapping("/eliminar/{codigo_venta}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Long codigo_venta) {
        ventasService.deleteVenta(codigo_venta);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/editar/{codigo_venta}")
    public ResponseEntity<VentaDTO> editVenta(@PathVariable Long codigo_venta,
                                              @Valid @RequestBody VentaDTO ventaDTO) {
        return ResponseEntity.ok(ventasService.editVenta(codigo_venta, ventaDTO));
    }

    @GetMapping("/productos/{codigo_venta}")
    public ResponseEntity<List<ProductoDTO>> getProductosPorVenta(@PathVariable Long codigo_venta) {
        return ResponseEntity.ok(ventasService.getProductosByVenta(codigo_venta));
    }

    @GetMapping("/fecha/{fecha_venta}")
    public ResponseEntity<DatosVentaDTO> getDatosVenta(@PathVariable LocalDate fecha_venta) {
        return ResponseEntity.ok(ventasService.getDatosVenta(fecha_venta));
    }

    @GetMapping("/mayor_venta")
    public ResponseEntity<DatosVentaMayorDTO> getDatosMayorVenta() {
        return ResponseEntity.ok(ventasService.getDatosVentaMayor());
    }
}
