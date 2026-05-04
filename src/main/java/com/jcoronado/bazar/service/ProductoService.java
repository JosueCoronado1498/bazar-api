package com.jcoronado.bazar.service;

import com.jcoronado.bazar.dto.ProductoDTO;
import com.jcoronado.bazar.exception.ResourceNotFoundException;
import com.jcoronado.bazar.model.Producto;
import com.jcoronado.bazar.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    public ProductoDTO saveProducto(ProductoDTO productoDTO) {
        Producto producto = mapToEntity(productoDTO);
        Producto productoCreado = productoRepository.save(producto);

        return mapToDTO(productoCreado);
    }

    @Override
    public List<ProductoDTO> findProductos() {
        return productoRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public ProductoDTO findProductoById(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
        return mapToDTO(producto);
    }

    @Override
    public void deleteProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Producto no encontrado");
        }
        productoRepository.deleteById(id);
    }

    @Override
    public ProductoDTO editProducto(Long id, ProductoDTO productoDTO) {
        Producto existe = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        existe.setNombre(productoDTO.getNombre());
        existe.setCosto(productoDTO.getPrecio());
        existe.setCantidadDisponible(productoDTO.getCantidadDisponible());

        Producto actualizado = productoRepository.save(existe);

        return mapToDTO(actualizado);
    }

    @Override
    public List<ProductoDTO> productoFaltaStock() {
        return productoRepository.findByCantidadDisponibleLessThan(5)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    private ProductoDTO mapToDTO(Producto producto) {
        return new ProductoDTO(
                producto.getCodigo_producto(),
                producto.getNombre(),
                producto.getCosto(),
                producto.getCantidadDisponible()
        );
    }

    private Producto mapToEntity(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setCodigo_producto(dto.getCodigoProducto());
        producto.setNombre(dto.getNombre());
        producto.setCosto(dto.getPrecio());
        producto.setCantidadDisponible(dto.getCantidadDisponible());
        return producto;
    }


}
