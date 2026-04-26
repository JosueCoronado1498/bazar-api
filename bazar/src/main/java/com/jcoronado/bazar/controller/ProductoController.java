package com.jcoronado.bazar.controller;

import com.jcoronado.bazar.dto.ProductoDTO;
import com.jcoronado.bazar.service.IProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;


    @PostMapping("/crear")
    public ResponseEntity<ProductoDTO> saveProducto(@Valid
            @RequestBody ProductoDTO productoDTO) {
        ProductoDTO productoCreado = productoService.saveProducto(productoDTO);

        return ResponseEntity.status(201).body(productoCreado);
    }

    @GetMapping()
    public ResponseEntity<List<ProductoDTO>> getProductos() {

        return ResponseEntity.ok(productoService.findProductos());

    }

    @GetMapping("/{codigo_producto}")
    public ResponseEntity<ProductoDTO> getProductoById(@PathVariable Long codigo_producto) {
        return ResponseEntity.ok(productoService.findProductoById(codigo_producto));
    }

    @DeleteMapping("/eliminar/{codigo_producto}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long codigo_producto) {
        productoService.deleteProducto(codigo_producto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/editar/{codigo_producto}")
    public ResponseEntity<ProductoDTO> editProducto(@PathVariable Long codigo_producto,
                                                    @Valid @RequestBody ProductoDTO productoDTO) {
        return ResponseEntity.ok(productoService.editProducto(codigo_producto, productoDTO));
    }

    @GetMapping("/falta_stock")
    public ResponseEntity<List<ProductoDTO>> productoFaltaStock(){
        return ResponseEntity.ok(productoService.productoFaltaStock());
    }

}