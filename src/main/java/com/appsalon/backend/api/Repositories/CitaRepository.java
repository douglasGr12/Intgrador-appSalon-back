package com.appsalon.backend.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appsalon.backend.api.Entities.Cita;

import java.time.LocalDate;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    @Query("SELECT c FROM Cita c WHERE c.cita_fechaCita = :fecha")
    List<Cita> findByFechaCita(@Param("fecha") LocalDate fecha);

}
