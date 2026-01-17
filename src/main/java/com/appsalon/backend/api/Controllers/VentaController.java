package com.appsalon.backend.api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.List;

import com.appsalon.backend.api.Entities.*;
import com.appsalon.backend.api.Repositories.*;

@RestController
@RequestMapping("/venta")
public class VentaController {

    @Autowired private VentaRepository ventaRepository;
    @Autowired private ClienteRepository clienteRepository;
    @Autowired private EmpleadoRepository empleadoRepository;
    @Autowired private NegocioRepository negocioRepository;

    // 🔹 GET ALL
    @GetMapping
    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }

    // 🔹 GET BY ID
    @GetMapping("/{id}")
    public Venta getVentaById(@PathVariable Long id) {
        return ventaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }

    // 🔹 CREATE
    @PostMapping
    public Venta createVenta(
            @RequestParam Long idCliente,
            @RequestParam Long idEmpleado,
            @RequestParam Long idNegocio,
            @RequestBody Venta venta) {

        Cliente cliente = clienteRepository.findById(idCliente)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Empleado empleado = empleadoRepository.findById(idEmpleado)
            .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        Negocio negocio = negocioRepository.findById(idNegocio)
            .orElseThrow(() -> new RuntimeException("Negocio no encontrado"));

        venta.setCliente(cliente);
        venta.setEmpleado(empleado);
        venta.setNegocio(negocio);
        venta.setVenta_fecha(LocalDateTime.now());
        venta.setVenta_total(BigDecimal.ZERO); // 🔥 IMPORTANTE

        return ventaRepository.save(venta);
    }

    // 🔹 GET POR RANGO DE FECHAS
    @GetMapping("/rango")
    public List<Venta> getVentasPorRango(
            @RequestParam String inicio,
            @RequestParam String fin) {

        return ventaRepository.findByVentaFechaBetween(
            LocalDateTime.parse(inicio),
            LocalDateTime.parse(fin)
        );
    }

    // 🔹 DELETE
    @DeleteMapping("/{id}")
    public String deleteVenta(@PathVariable Long id) {
        Venta venta = ventaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        ventaRepository.delete(venta);
        return "Venta eliminada con éxito";
    }
}
