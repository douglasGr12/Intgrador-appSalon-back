package com.appsalon.backend.api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.appsalon.backend.api.Entities.*;
import com.appsalon.backend.api.Repositories.*;

@RestController
@RequestMapping("/comision")
public class ComisionController {

    @Autowired
    private ComisionRepository comisionRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private NegocioRepository negocioRepository;

    // 🔹 GET ALL
    @GetMapping
    public List<Comision> getAllComisiones() {
        return comisionRepository.findAll();
    }

    @GetMapping("/empleado/{idEmpleado}")
    public List<Comision> getComisionesPorEmpleado(@PathVariable Long idEmpleado) {
        return comisionRepository.findByEmpleadoId(idEmpleado);
    }


    // 🔹 CREATE
    @PostMapping
    public Comision createComision(
            @RequestParam Long idEmpleado,
            @RequestParam Long idVenta,
            @RequestParam Long idNegocio,
            @RequestParam BigDecimal monto) {

        Empleado empleado = empleadoRepository.findById(idEmpleado)
            .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        Venta venta = ventaRepository.findById(idVenta)
            .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        Negocio negocio = negocioRepository.findById(idNegocio)
            .orElseThrow(() -> new RuntimeException("Negocio no encontrado"));

        Comision comision = new Comision();
        comision.setEmpleado(empleado);
        comision.setVenta(venta);
        comision.setNegocio(negocio);
        comision.setComi_monto(monto);
        comision.setComi_fechaGenerada(LocalDate.now());

        return comisionRepository.save(comision);
    }

    // 🔹 GET POR RANGO DE FECHAS
    @GetMapping("/rango")
    public List<Comision> getComisionesPorRango(
            @RequestParam String inicio,
            @RequestParam String fin) {

        return comisionRepository.findByFechaGeneradaBetween(
            LocalDate.parse(inicio),
            LocalDate.parse(fin)
        );
    }

    // 🔹 DELETE
    @DeleteMapping("/{id}")
    public String deleteComision(@PathVariable Long id) {
        comisionRepository.deleteById(id);
        return "Comisión eliminada con éxito";
    }
}
