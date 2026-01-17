package com.appsalon.backend.api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import com.appsalon.backend.api.Entities.*;
import com.appsalon.backend.api.Repositories.*;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private NegocioRepository negocioRepository;

    // 🔹 GET ALL
    @GetMapping
    public List<Compra> getAllCompras() {
        return compraRepository.findAll();
    }

    // 🔹 GET BY ID
    @GetMapping("/{id}")
    public Compra getCompraById(@PathVariable Long id) {
        return compraRepository.findById(id)
            .orElseThrow(() -> new RuntimeException(
                "Compra no encontrada con id: " + id
            ));
    }

    // 🔹 CREATE
    @PostMapping
    public Compra createCompra(
            @RequestParam Long idProveedor,
            @RequestParam Long idNegocio,
            @RequestBody Compra compra) {

        Proveedor proveedor = proveedorRepository.findById(idProveedor)
            .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        Negocio negocio = negocioRepository.findById(idNegocio)
            .orElseThrow(() -> new RuntimeException("Negocio no encontrado"));

        compra.setProveedor(proveedor);
        compra.setNegocio(negocio);
        compra.setComp_fechaCompra(LocalDate.now());

        return compraRepository.save(compra);
    }

    // 🔹 UPDATE
    @PutMapping("/{id}")
    public Compra updateCompra(
            @PathVariable Long id,
            @RequestBody Compra compraDetails) {

        Compra compra = compraRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Compra no encontrada"));

        compra.setComp_comprobante(compraDetails.getComp_comprobante());
        compra.setComp_fechaCompra(compraDetails.getComp_fechaCompra());

        return compraRepository.save(compra);
    }

    // 🔹 DELETE
    @DeleteMapping("/{id}")
    public String deleteCompra(@PathVariable Long id) {
        compraRepository.deleteById(id);
        return "Compra eliminada con éxito";
    }
}
