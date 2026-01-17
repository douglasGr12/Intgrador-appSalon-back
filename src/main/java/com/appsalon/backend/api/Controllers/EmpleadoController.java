package com.appsalon.backend.api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.appsalon.backend.api.Entities.*;
import com.appsalon.backend.api.Repositories.*;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private NegocioRepository negocioRepository;

    // 🔹 GET ALL
    @GetMapping
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    // 🔹 GET BY ID
    @GetMapping("/{id}")
    public Empleado getEmpleadoById(@PathVariable Long id) {
        return empleadoRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("No se encontró el empleado con id: " + id)
            );
    }

    // 🔹 CREATE
    @PostMapping
    public Empleado createEmpleado(
            @RequestParam Long idUsuario,
            @RequestParam Long idNegocio,
            @RequestBody Empleado empleado) {

        Usuario usuario = usuarioRepository.findById(idUsuario)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Negocio negocio = negocioRepository.findById(idNegocio)
            .orElseThrow(() -> new RuntimeException("Negocio no encontrado"));

        empleado.setUsuario(usuario);
        empleado.setNegocio(negocio);

        return empleadoRepository.save(empleado);
    }

    // 🔹 UPDATE
    @PostMapping("/{id}")
    public Empleado updateEmpleado(
            @PathVariable Long id,
            @RequestBody Empleado empleadoDetails) {

        Empleado empleado = empleadoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        empleado.setEmp_cargo(empleadoDetails.getEmp_cargo());
        empleado.setEmp_disponibilidad(empleadoDetails.getEmp_disponibilidad());
        empleado.setEmp_estado(empleadoDetails.getEmp_estado());

        return empleadoRepository.save(empleado);
    }

    // 🔹 DELETE
    @DeleteMapping("/{id}")
    public String deleteEmpleado(@PathVariable Long id) {

        Empleado empleado = empleadoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        empleadoRepository.delete(empleado);
        return "Empleado eliminado con éxito";
    }
}
