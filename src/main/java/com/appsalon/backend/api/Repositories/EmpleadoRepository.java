package com.appsalon.backend.api.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appsalon.backend.api.Entities.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    @Query("SELECT e FROM Empleado e WHERE e.negocio.negocio_id = :idNegocio")
    List<Empleado> findByNegocioId(@Param("idNegocio") Long idNegocio);
}
