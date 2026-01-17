package com.appsalon.backend.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsalon.backend.api.Entities.Rol;


public interface RolRepository extends JpaRepository<Rol, Long> {

}
