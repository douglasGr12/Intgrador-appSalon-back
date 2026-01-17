package com.appsalon.backend.api.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import com.appsalon.backend.api.Entities.*;
import com.appsalon.backend.api.Repositories.*;

@RestController
@RequestMapping("/cita")
public class CitaController {

    @Autowired private CitaRepository citaRepository;
    @Autowired private ClienteRepository clienteRepository;
    @Autowired private ServicioRepository servicioRepository;
    @Autowired private EmpleadoRepository empleadoRepository;
    @Autowired private NegocioRepository negocioRepository;

    // 🔹 GET ALL
    @GetMapping
    public List<Cita> getAllCitas() {
        return citaRepository.findAll();
    }

    // 🔹 GET BY ID
    @GetMapping("/{id}")
    public Cita getCitaById(@PathVariable Long id) {
        return citaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
    }

    // 🔹 CREATE
    @PostMapping
    public Cita createCita(
            @RequestParam Long idCliente,
            @RequestParam Long idServicio,
            @RequestParam(required = false) Long idEmpleado,
            @RequestParam Long idNegocio,
            @RequestBody Cita cita) {

        Cliente cliente = clienteRepository.findById(idCliente)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Servicio servicio = servicioRepository.findById(idServicio)
            .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        Negocio negocio = negocioRepository.findById(idNegocio)
            .orElseThrow(() -> new RuntimeException("Negocio no encontrado"));

        cita.setCliente(cliente);
        cita.setServicio(servicio);
        cita.setNegocio(negocio);
        cita.setCita_estado(EstadoCita.PROGRAMADA);

        if (idEmpleado != null) {
            Empleado empleado = empleadoRepository.findById(idEmpleado)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
            cita.setEmpleado(empleado);
        }

        return citaRepository.save(cita);
    }

    // 🔹 UPDATE ESTADO
    @PostMapping("/{id}/estado")
    public Cita updateEstado(
            @PathVariable Long id,
            @RequestParam String estado) {

        Cita cita = citaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        cita.setCita_estado(EstadoCita.valueOf(estado.toUpperCase()));
        return citaRepository.save(cita);
    }

    // 🔹 GET POR FECHA
    @GetMapping("/fecha/{fecha}")
    public List<Cita> getCitasPorFecha(@PathVariable String fecha) {
        return citaRepository.findByFechaCita(LocalDate.parse(fecha));
    }

    // 🔹 DELETE
    @DeleteMapping("/{id}")
    public String deleteCita(@PathVariable Long id) {
        citaRepository.deleteById(id);
        return "Cita eliminada con éxito";
    }
}