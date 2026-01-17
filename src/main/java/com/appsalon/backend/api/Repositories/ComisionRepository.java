package com.appsalon.backend.api.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appsalon.backend.api.Entities.Comision;
import java.time.LocalDate;
import java.util.List;

public interface ComisionRepository extends JpaRepository<Comision, Long> {

    @Query("SELECT c FROM Comision c WHERE c.empleado.emp_id = :idEmpleado")
    List<Comision> findByEmpleadoId(@Param("idEmpleado") Long idEmpleado);

    @Query("""
        SELECT c FROM Comision c
        WHERE c.comi_fechaGenerada BETWEEN :inicio AND :fin
    """)
    List<Comision> findByFechaGeneradaBetween(
        @Param("inicio") LocalDate inicio,
        @Param("fin") LocalDate fin
    );
}


