package com.appsalon.backend.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsalon.backend.api.Entities.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
