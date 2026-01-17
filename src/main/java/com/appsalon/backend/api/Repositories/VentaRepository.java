package com.appsalon.backend.api.Repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appsalon.backend.api.Entities.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    @Query("""
        SELECT v
        FROM Venta v
        WHERE v.venta_fecha BETWEEN :inicio AND :fin
    """)
    List<Venta> findByVentaFechaBetween(
        @Param("inicio") LocalDateTime inicio,
        @Param("fin") LocalDateTime fin
    );

    @Query("""
        SELECT v
        FROM Venta v
        WHERE v.empleado.emp_id = :idEmpleado
    """)
    List<Venta> findByEmpleadoId(@Param("idEmpleado") Long idEmpleado);
}
