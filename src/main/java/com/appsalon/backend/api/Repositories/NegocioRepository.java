package com.appsalon.backend.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsalon.backend.api.Entities.Negocio;

public interface NegocioRepository extends JpaRepository<Negocio, Long> {

}
