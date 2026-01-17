package com.appsalon.backend.api.Controllers;

import com.appsalon.backend.api.Entities.Rol;
import com.appsalon.backend.api.Repositories.RolRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolController {

    private final RolRepository rolRepository;

    public RolController(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    // 🔹 GET ALL
    @GetMapping
    public List<Rol> getAllRoles() {
        return rolRepository.findAll();
    }

    // 🔹 GET BY ID
    @GetMapping("/{id}")
    public Rol getRolById(@PathVariable Long id) {
        return rolRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Rol no encontrado con id: " + id)
            );
    }

    // 🔹 CREATE
    @PostMapping
    public Rol createRol(@RequestBody Rol rol) {
        return rolRepository.save(rol);
    }

    // 🔹 UPDATE
    @PutMapping("/{id}")
    public Rol updateRol(
            @PathVariable Long id,
            @RequestBody Rol rolDetails) {

        Rol rol = rolRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Rol no encontrado")
            );

        rol.setRol_nombre(rolDetails.getRol_nombre());
        return rolRepository.save(rol);
    }

    // 🔹 DELETE
    @DeleteMapping("/{id}")
    public void deleteRol(@PathVariable Long id) {

        Rol rol = rolRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Rol no encontrado")
            );

        rolRepository.delete(rol);
    }
}
