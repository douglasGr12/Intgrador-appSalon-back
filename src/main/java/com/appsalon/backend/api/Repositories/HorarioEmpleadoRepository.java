package com.appsalon.backend.api.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appsalon.backend.api.Entities.HorarioEmpleado;

public interface HorarioEmpleadoRepository extends JpaRepository<HorarioEmpleado, Long> {

    @Query("""
        SELECT h
        FROM HorarioEmpleado h
        WHERE h.empleado.emp_id = :idEmpleado
    """)
    List<HorarioEmpleado> findByEmpleadoId(@Param("idEmpleado") Long idEmpleado);
}
