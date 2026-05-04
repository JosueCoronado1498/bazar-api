package com.jcoronado.bazar.repository;

import com.jcoronado.bazar.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByCantidadDisponibleLessThan(int cantidad_disponible);

}
