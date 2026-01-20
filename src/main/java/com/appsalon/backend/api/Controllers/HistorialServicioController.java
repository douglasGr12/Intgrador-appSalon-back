package com.appsalon.backend.api.Controllers;

import com.appsalon.backend.api.Entities.*;
import com.appsalon.backend.api.Repositories.*;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/historial-servicio")
public class HistorialServicioController {

      @Autowired
      private HistorialServicioRepository historialRepository;

      @Autowired
      private ClienteRepository clienteRepository;

      @Autowired
      private ServicioRepository servicioRepository;

      @Autowired
      private EmpleadoRepository empleadoRepository;

      @Autowired
      private NegocioRepository negocioRepository;

      // 🔹 GET ALL
      @GetMapping
      public List<HistorialServicio> getAllHistorial() {
            return historialRepository.findAll();
      }

      // 🔹 GET POR CLIENTE
      @GetMapping("/cliente/{idCliente}")
      public List<HistorialServicio> getHistorialPorCliente(@PathVariable Long idCliente) {
            return historialRepository.findByClienteId(idCliente);
      }

      // 🔹 CREATE
      @PostMapping
      public HistorialServicio createHistorial(
                  @RequestParam Long idCliente,
                  @RequestParam Long idServicio,
                  @RequestParam Long idEmpleado,
                  @RequestParam Long idNegocio,
                  @RequestBody HistorialServicio historial) {

            Cliente cliente = clienteRepository.findById(idCliente)
                        .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

            Servicio servicio = servicioRepository.findById(idServicio)
                        .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

            Empleado empleado = empleadoRepository.findById(idEmpleado)
                        .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

            Negocio negocio = negocioRepository.findById(idNegocio)
                        .orElseThrow(() -> new RuntimeException("Negocio no encontrado"));

            historial.setCliente(cliente);
            historial.setServicio(servicio);
            historial.setEmpleado(empleado);
            historial.setNegocio(negocio);
            historial.setHistorial_fecha(LocalDate.now());

            return historialRepository.save(historial);
      }

      @GetMapping("/negocio/{id}")
      public List<Empleado> getEmpleadosPorNegocio(@PathVariable Long id) {
            return empleadoRepository.findByNegocioId(id);
      }

      // 🔹 UPDATE (EDITAR)
      @PutMapping("/{id}")
      public HistorialServicio updateHistorial(
                  @PathVariable Long id,
                  @RequestParam Long idCliente,
                  @RequestParam Long idServicio,
                  @RequestParam Long idEmpleado,
                  @RequestParam Long idNegocio,
                  @RequestBody HistorialServicio historialDetails) {

            HistorialServicio historial = historialRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Historial no encontrado con id: " + id));

            Cliente cliente = clienteRepository.findById(idCliente)
                        .orElseThrow(() -> new RuntimeException("Cliente no válido"));

            Servicio servicio = servicioRepository.findById(idServicio)
                        .orElseThrow(() -> new RuntimeException("Servicio no válido"));

            Empleado empleado = empleadoRepository.findById(idEmpleado)
                        .orElseThrow(() -> new RuntimeException("Empleado no válido"));

            Negocio negocio = negocioRepository.findById(idNegocio)
                        .orElseThrow(() -> new RuntimeException("Negocio no válido"));

            historial.setCliente(cliente);
            historial.setServicio(servicio);
            historial.setEmpleado(empleado);
            historial.setNegocio(negocio);
            historial.setHistorial_detalle(historialDetails.getHistorial_detalle());
            historial.setHistorial_fecha(historialDetails.getHistorial_fecha());

            return historialRepository.save(historial);
      }

      // 🔹 GET POR RANGO DE FECHAS
      @GetMapping("/rango")
      public List<HistorialServicio> getHistorialPorRango(
                  @RequestParam String inicio,
                  @RequestParam String fin) {

            return historialRepository.findByFechaBetween(
                        LocalDate.parse(inicio),
                        LocalDate.parse(fin));
      }

      // 🔹 DELETE
      @DeleteMapping("/{id}")
      public String deleteHistorial(@PathVariable Long id) {

            HistorialServicio historial = historialRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Historial no encontrado"));

            historialRepository.delete(historial);

            return "Historial de servicio eliminado con éxito";
      }
}
