package com.appsalon.backend.api.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.appsalon.backend.api.Entities.*;
import com.appsalon.backend.api.Repositories.*;

@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private NegocioRepository negocioRepository;

    // 🔹 GET ALL
    @GetMapping
    public List<Proveedor> getAllProveedores() {
        return proveedorRepository.findAll();
    }

    // 🔹 GET BY ID
    @GetMapping("/{id}")
    public Proveedor getProveedorById(@PathVariable Long id) {
        return proveedorRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Proveedor no encontrado con id: " + id)
            );
    }

    // 🔹 CREATE
    @PostMapping
    public Proveedor createProveedor(
            @RequestParam Long idNegocio,
            @RequestBody Proveedor proveedor) {

        Negocio negocio = negocioRepository.findById(idNegocio)
            .orElseThrow(() ->
                new RuntimeException("Negocio no encontrado")
            );

        proveedor.setNegocio(negocio);
        return proveedorRepository.save(proveedor);
    }

    // 🔹 UPDATE
    @PutMapping("/{id}")
    public Proveedor updateProveedor(
            @PathVariable Long id,
            @RequestBody Proveedor proveedorDetails) {

        Proveedor proveedor = proveedorRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Proveedor no encontrado")
            );

        proveedor.setProve_nombre(proveedorDetails.getProve_nombre());
        proveedor.setProve_telefono(proveedorDetails.getProve_telefono());
        proveedor.setProve_correo(proveedorDetails.getProve_correo());

        return proveedorRepository.save(proveedor);
    }

    // 🔹 DELETE
    @DeleteMapping("/{id}")
    public String deleteProveedor(@PathVariable Long id) {

        Proveedor proveedor = proveedorRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Proveedor no encontrado")
            );

        proveedorRepository.delete(proveedor);
        return "Proveedor eliminado con éxito";
    }
}
