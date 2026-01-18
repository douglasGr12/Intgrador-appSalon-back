package com.appsalon.backend.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.appsalon.backend.api.Entities.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsuEmail(String usuEmail);

}

