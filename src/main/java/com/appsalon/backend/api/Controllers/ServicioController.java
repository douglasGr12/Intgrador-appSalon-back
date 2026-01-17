package com.appsalon.backend.api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.appsalon.backend.api.Entities.Servicio;
import com.appsalon.backend.api.Entities.Negocio;
import com.appsalon.backend.api.Repositories.*;

@RestController
@RequestMapping("/servicio")
public class ServicioController {

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private NegocioRepository negocioRepository;

    // 🔹 GET ALL
    @GetMapping
    public List<Servicio> getAllServicios() {
        return servicioRepository.findAll();
    }

    // 🔹 GET BY ID
    @GetMapping("/{id}")
    public Servicio getServicioById(@PathVariable Long id) {
        return servicioRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("No se encontró el servicio con id: " + id)
            );
    }

    // 🔹 CREATE
    @PostMapping
    public Servicio createServicio(
            @RequestParam Long idNegocio,
            @RequestBody Servicio servicio) {

        Negocio negocio = negocioRepository.findById(idNegocio)
            .orElseThrow(() ->
                new RuntimeException("Negocio no encontrado")
            );

        servicio.setNegocio(negocio);
        servicio.setServ_estado(true);

        return servicioRepository.save(servicio);
    }

    // 🔹 UPDATE
    @PutMapping("/{id}")
    public Servicio updateServicio(
            @PathVariable Long id,
            @RequestBody Servicio servicioDetails) {

        Servicio servicio = servicioRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Servicio no encontrado")
            );

        servicio.setServ_nombre(servicioDetails.getServ_nombre());
        servicio.setServ_precio(servicioDetails.getServ_precio());
        servicio.setServ_duracionMinutos(servicioDetails.getServ_duracionMinutos());
        servicio.setServ_estado(servicioDetails.getServ_estado());

        return servicioRepository.save(servicio);
    }

    // 🔹 DELETE (borrado lógico)
    @DeleteMapping("/{id}")
    public String deleteServicio(@PathVariable Long id) {

        Servicio servicio = servicioRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Servicio no encontrado")
            );

        servicio.setServ_estado(false);
        servicioRepository.save(servicio);

        return "Servicio desactivado con éxito";
    }
}
