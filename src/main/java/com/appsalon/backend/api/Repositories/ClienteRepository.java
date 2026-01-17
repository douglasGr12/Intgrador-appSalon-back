package com.appsalon.backend.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsalon.backend.api.Entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}

