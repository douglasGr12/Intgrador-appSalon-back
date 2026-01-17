package com.appsalon.backend.api.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appsalon.backend.api.Entities.HistorialServicio;

public interface HistorialServicioRepository extends JpaRepository<HistorialServicio, Long> {

    @Query("""
        SELECT h
        FROM HistorialServicio h
        WHERE h.cliente.cli_id = :idCliente
    """)
    List<HistorialServicio> findByClienteId(
        @Param("idCliente") Long idCliente
    );

    @Query("""
        SELECT h
        FROM HistorialServicio h
        WHERE h.historial_fecha BETWEEN :inicio AND :fin
    """)
    List<HistorialServicio> findByFechaBetween(
        @Param("inicio") LocalDate inicio,
        @Param("fin") LocalDate fin
    );
}
