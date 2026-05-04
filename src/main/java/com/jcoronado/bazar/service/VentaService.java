package com.jcoronado.bazar.service;

import com.jcoronado.bazar.dto.*;
import com.jcoronado.bazar.exception.BadRequestException;
import com.jcoronado.bazar.exception.ResourceNotFoundException;
import com.jcoronado.bazar.model.Cliente;
import com.jcoronado.bazar.model.DetalleVenta;
import com.jcoronado.bazar.model.Producto;
import com.jcoronado.bazar.model.Venta;
import com.jcoronado.bazar.repository.IClienteRepository;
import com.jcoronado.bazar.repository.IProductoRepository;
import com.jcoronado.bazar.repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
public class VentaService implements IVentasService{

    @Autowired
    private IVentaRepository ventaRepository;

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private IClienteRepository clienteRepository;

    @Override
    @Transactional
    public VentaDTO saveVenta(VentaDTO ventaDTO) {
        Venta venta = mapToEntity(ventaDTO);
        Venta ventaCreada = ventaRepository.save(venta);

        return mapToDTO(ventaCreada);
    }

    @Override
    public List<VentaDTO> getVentas() {
        return ventaRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public VentaDTO getVentaById(Long codigo_venta) {
        Venta venta = ventaRepository.findById(codigo_venta)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada"));

        return mapToDTO(venta);
    }

    @Override
    public void deleteVenta(Long codigo_venta) {
        if (!ventaRepository.existsById(codigo_venta)) {
            throw new ResourceNotFoundException("Venta no encontrada");
        }
        ventaRepository.deleteById(codigo_venta);
    }

    @Override
    public VentaDTO editVenta(Long codigo_venta, VentaDTO ventaDTO) {
        Venta existe = ventaRepository.findById(codigo_venta)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada"));

        Venta ventaActualizada = mapToEntity(ventaDTO);
        ventaActualizada.setCodigo_venta(codigo_venta);

        Venta guardada = ventaRepository.save(ventaActualizada);

        return mapToDTO(guardada);
    }

    @Override
    public List<ProductoDTO> getProductosByVenta(Long codigo_venta) {
        Venta venta = ventaRepository.findById(codigo_venta)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada"));

        return venta.getDetalleVentas()
                .stream()
                .map(detalleVenta -> mapProductoToDTO(detalleVenta.getProducto()))
                .toList();
    }

    @Override
    public DatosVentaDTO getDatosVenta(LocalDate fechaVenta) {
        List<Venta> ventas = ventaRepository.findByFechaVenta(fechaVenta);

        int cantidadVentas = ventas.size();

        Double total = ventas.stream()
                .mapToDouble(Venta::getTotal)
                .sum();

        return new DatosVentaDTO(total, cantidadVentas);
    }

    @Override
    public DatosVentaMayorDTO getDatosVentaMayor() {
        List<Venta> listaVentas = ventaRepository.findAll();

        Venta ventaMayor = listaVentas.stream()
                .max(Comparator.comparing(Venta::getTotal))
                .orElse(null);
        if (ventaMayor == null) {
            return null;
        }
        Long codigo_venta = ventaMayor.getCodigo_venta();
        Double total = ventaMayor.getTotal();
        int cantidad_productos = ventaMayor.getDetalleVentas().size();
        String nombre_cliente = ventaMayor.getUnCliente().getNombre();
        String apellido_cliente = ventaMayor.getUnCliente().getApellido();

        return new DatosVentaMayorDTO(codigo_venta, total, cantidad_productos, nombre_cliente, apellido_cliente);
    }

    private VentaDTO mapToDTO(Venta venta) {
        List<DetalleVentaDTO> detalleVentaDTO = venta.getDetalleVentas().stream()
                .map(d -> new DetalleVentaDTO(
                        d.getProducto().getCodigo_producto(),
                        d.getCantidad()
                )).toList();

        return new VentaDTO(
                venta.getCodigo_venta(),
                venta.getFechaVenta(),
                venta.getTotal(),
                detalleVentaDTO,
                venta.getUnCliente().getId_cliente()
        );
    }

    private Venta mapToEntity(VentaDTO ventaDTO) {
        Venta venta = new Venta();
        venta.setFechaVenta(ventaDTO.getFechaVenta());

        Cliente cliente = clienteRepository
                .findById(ventaDTO.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        venta.setUnCliente(cliente);

        List<DetalleVenta> detalleVentas = ventaDTO.getDetalleVentaDTO().stream().map(detalleVentaDTO -> {

            Producto producto = productoRepository
                    .findById(detalleVentaDTO.getProductoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

            //Validar Stock
            if (producto.getCantidadDisponible() < detalleVentaDTO.getCantidad()) {
                throw new BadRequestException("Stock insuficiente para producto: " + producto.getNombre());
            }

            //Descontar Stock
            producto.setCantidadDisponible(
                    producto.getCantidadDisponible() - detalleVentaDTO.getCantidad()
            );

            productoRepository.save(producto);

            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setProducto(producto);
            detalleVenta.setCantidad(detalleVentaDTO.getCantidad());
            detalleVenta.setPrecioUnitario(producto.getCosto());
            detalleVenta.setVenta(venta);

            return detalleVenta;
        }).toList();

        venta.setDetalleVentas(detalleVentas);

        //Calcular Total
        Double total = detalleVentas.stream()
                .mapToDouble(d -> d.getCantidad() * d.getPrecioUnitario())
                .sum();

        venta.setTotal(total);

        return venta;
    }

    private ProductoDTO mapProductoToDTO(Producto producto) {
        return new ProductoDTO(
                producto.getCodigo_producto(),
                producto.getNombre(),
                producto.getCosto(),
                producto.getCantidadDisponible()
        );
    }


}
