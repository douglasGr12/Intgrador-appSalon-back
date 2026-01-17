package com.appsalon.backend.api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.appsalon.backend.api.Entities.Negocio;
import com.appsalon.backend.api.Repositories.NegocioRepository;

@RestController
@RequestMapping("/negocio")
public class NegocioController {

    @Autowired
    private NegocioRepository negocioRepository;

    // 🔹 GET ALL
    @GetMapping
    public List<Negocio> getAllNegocios() {
        return negocioRepository.findAll();
    }

    // 🔹 GET BY ID
    @GetMapping("/{id}")
    public Negocio getNegocioById(@PathVariable Long id) {
        return negocioRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("No se encontró el negocio con el id: " + id)
            );
    }

    // 🔹 CREATE
    @PostMapping
    public Negocio createNegocio(@RequestBody Negocio negocio) {
        return negocioRepository.save(negocio);
    }

    // 🔹 UPDATE
    @PutMapping("/{id}")
    public Negocio updateNegocio(
            @PathVariable Long id,
            @RequestBody Negocio negocioDetails) {

        Negocio negocio = negocioRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("No se encontró el negocio con el id: " + id)
            );

        negocio.setNegocio_nombre(negocioDetails.getNegocio_nombre());
        negocio.setNegocio_apertura(negocioDetails.getNegocio_apertura());
        negocio.setNegocio_cierre(negocioDetails.getNegocio_cierre());
        negocio.setNegocio_logo(negocioDetails.getNegocio_logo());
        negocio.setNegocio_parametros(negocioDetails.getNegocio_parametros());

        return negocioRepository.save(negocio);
    }

    // 🔹 DELETE
    @DeleteMapping("/{id}")
    public String deleteNegocio(@PathVariable Long id) {

        Negocio negocio = negocioRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("No se encontró el negocio con el id: " + id)
            );

        negocioRepository.delete(negocio);
        return "Negocio eliminado con éxito";
    }
}
