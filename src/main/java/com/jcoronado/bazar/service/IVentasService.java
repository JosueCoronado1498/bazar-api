package com.jcoronado.bazar.service;

import com.jcoronado.bazar.dto.DatosVentaDTO;
import com.jcoronado.bazar.dto.DatosVentaMayorDTO;
import com.jcoronado.bazar.dto.ProductoDTO;
import com.jcoronado.bazar.dto.VentaDTO;

import java.time.LocalDate;
import java.util.List;

public interface IVentasService {

    //crear
    public VentaDTO saveVenta(VentaDTO ventaDTO);

    //traer
    public List<VentaDTO> getVentas();

    //traer por id
    public VentaDTO getVentaById(Long codigo_venta);

    //eliminar
    public void deleteVenta(Long codigo_venta);

    //editar
    public VentaDTO editVenta(Long codigo_venta, VentaDTO ventaDTO);

    //traer productos por venta
    public List<ProductoDTO> getProductosByVenta(Long codigo_venta);

    //traer ventas determinado dia
    public DatosVentaDTO getDatosVenta(LocalDate fechaVenta);

    //traer datos mayor venta
    public DatosVentaMayorDTO getDatosVentaMayor();

}
