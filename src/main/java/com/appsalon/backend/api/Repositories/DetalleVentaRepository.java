package com.appsalon.backend.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsalon.backend.api.Entities.DetalleVenta;
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {

}
