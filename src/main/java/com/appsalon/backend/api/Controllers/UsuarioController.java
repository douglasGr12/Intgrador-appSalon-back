package com.appsalon.backend.api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

import java.util.List;

import com.appsalon.backend.api.DTO.LoginRequest;
import com.appsalon.backend.api.DTO.LoginResponse;
import com.appsalon.backend.api.Entities.*;
import com.appsalon.backend.api.Repositories.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    // 🔹 GET ALL
    @GetMapping
    public List<Usuario> getAllUsers() {
        return usuarioRepository.findAll();
    }

    // 🔹 GET BY ID
    @GetMapping("/{id}")
    public Usuario getUserById(@PathVariable Long id) {
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    // 🔹 CREATE
    @PostMapping
    public Usuario createUser(@RequestBody Usuario usuario) {
    
        System.out.println("USUARIO: " + usuario.getUsuEmail());
    
        if (usuario.getRol() == null) {
            throw new RuntimeException("ROL ES NULL");
        }
    
        System.out.println("ROL ID: " + usuario.getRol().getRol_id());

        Rol rol = rolRepository.findById(usuario.getRol().getRol_id())
            .orElseThrow(() -> new RuntimeException("Rol no válido"));
    
        usuario.setRol(rol);
        usuario.setUsu_estado("ACTIVO");
    
        return usuarioRepository.save(usuario);
    }


    // 🔹 UPDATE
    @PutMapping("/{id}")
    public Usuario updateUsuario(@RequestBody Usuario usuarioDetails, @PathVariable Long id) {

        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

        Rol rol = rolRepository.findById(usuarioDetails.getRol().getRol_id())
            .orElseThrow(() -> new RuntimeException("Rol no válido"));

        usuario.setUsu_nombre(usuarioDetails.getUsu_nombre());
        usuario.setUsu_apellido(usuarioDetails.getUsu_apellido());
        usuario.setUsuEmail(usuarioDetails.getUsuEmail());
        usuario.setUsu_telefono(usuarioDetails.getUsu_telefono());
        usuario.setUsu_direccion(usuarioDetails.getUsu_direccion());
        usuario.setUsu_clave(usuarioDetails.getUsu_clave());
        usuario.setUsu_estado(usuarioDetails.getUsu_estado());
        usuario.setUsu_foto(usuarioDetails.getUsu_foto());
        usuario.setRol(rol);

        return usuarioRepository.save(usuario);
    }

    // 🔹 DELETE
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

        usuarioRepository.delete(usuario);
        return "Usuario eliminado con éxito";
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        Usuario usuario = usuarioRepository
            .findByUsuEmail(request.getEmail())
            .orElseThrow(() ->
                new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas")
            );

        // comparación directa (PLANO)
        if (!usuario.getUsu_clave().equals(request.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
        }

        if (!"ACTIVO".equals(usuario.getUsu_estado())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuario inactivo");
        }

        return ResponseEntity.ok(
            new LoginResponse(
                usuario.getUsu_id(),
                usuario.getUsu_nombre(),
                usuario.getUsuEmail(),
                usuario.getRol().getRol_id(),
                usuario.getRol().getRol_nombre()
            )
        );
    }
}
