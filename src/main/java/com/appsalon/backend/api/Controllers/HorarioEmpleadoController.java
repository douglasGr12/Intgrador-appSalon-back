package com.appsalon.backend.api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.appsalon.backend.api.Entities.*;
import com.appsalon.backend.api.Repositories.*;

@RestController
@RequestMapping("/horario-empleado")
public class HorarioEmpleadoController {

    @Autowired
    private HorarioEmpleadoRepository horarioEmpleadoRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    // 🔹 GET ALL
    @GetMapping
    public List<HorarioEmpleado> getAllHorarios() {
        return horarioEmpleadoRepository.findAll();
    }

    // 🔹 GET POR EMPLEADO
    @GetMapping("/empleado/{idEmpleado}")
    public List<HorarioEmpleado> getHorariosPorEmpleado(@PathVariable Long idEmpleado) {
        return horarioEmpleadoRepository.findByEmpleadoId(idEmpleado);
    }

    // 🔹 CREATE
    @PostMapping
    public HorarioEmpleado createHorario(
            @RequestParam Long idEmpleado,
            @RequestBody HorarioEmpleado horario) {

        Empleado empleado = empleadoRepository.findById(idEmpleado)
            .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        horario.setEmpleado(empleado);
        return horarioEmpleadoRepository.save(horario);
    }

    // 🔹 UPDATE
    @PostMapping("/{id}")
    public HorarioEmpleado updateHorario(
            @PathVariable Long id,
            @RequestBody HorarioEmpleado horarioDetails) {

        HorarioEmpleado horario = horarioEmpleadoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Horario no encontrado"));

        horario.setDiaSemana(horarioDetails.getDiaSemana());
        horario.setHoraInicio(horarioDetails.getHoraInicio());
        horario.setHoraFin(horarioDetails.getHoraFin());
        horario.setEstado(horarioDetails.getEstado());

        return horarioEmpleadoRepository.save(horario);
    }

    // 🔹 DELETE
    @DeleteMapping("/{id}")
    public String deleteHorario(@PathVariable Long id) {

        HorarioEmpleado horario = horarioEmpleadoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Horario no encontrado"));

        horarioEmpleadoRepository.delete(horario);
        return "Horario eliminado con éxito";
    }
}
