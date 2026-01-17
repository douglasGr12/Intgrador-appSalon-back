package com.appsalon.backend.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsalon.backend.api.Entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

}

