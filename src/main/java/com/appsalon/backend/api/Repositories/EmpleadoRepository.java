package com.appsalon.backend.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsalon.backend.api.Entities.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
}
