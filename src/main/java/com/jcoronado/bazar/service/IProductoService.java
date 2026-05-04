package com.jcoronado.bazar.service;

import com.jcoronado.bazar.dto.ProductoDTO;
import com.jcoronado.bazar.model.Producto;

import java.util.List;

public interface IProductoService {

    //crear
    public ProductoDTO saveProducto(ProductoDTO productoDTO);

    //traer
    public List<ProductoDTO> findProductos();

    //traer por id
    public ProductoDTO findProductoById(Long id);

    //eliminar
    public void deleteProducto(Long id);

    //modificar
    public ProductoDTO editProducto(Long id, ProductoDTO productoDTO);

    //cantidad disponible
    public List<ProductoDTO> productoFaltaStock();

}
