package com.appsalon.backend.api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.appsalon.backend.api.Entities.Producto;
import com.appsalon.backend.api.Entities.Negocio;
import com.appsalon.backend.api.Repositories.ProductoRepository;
import com.appsalon.backend.api.Repositories.NegocioRepository;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private NegocioRepository negocioRepository;

    // 🔹 GET ALL
    @GetMapping
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    // 🔹 GET BY ID
    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable Long id) {
        return productoRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Producto no encontrado con id: " + id)
            );
    }

    // 🔹 CREATE
    @PostMapping
    public Producto createProducto(
            @RequestParam Long idNegocio,
            @RequestBody Producto producto) {

        Negocio negocio = negocioRepository.findById(idNegocio)
            .orElseThrow(() ->
                new RuntimeException("Negocio no encontrado")
            );

        producto.setNegocio(negocio);
        producto.setProdu_estado(true);

        return productoRepository.save(producto);
    }

    // 🔹 UPDATE
    @PutMapping("/{id}")
    public Producto updateProducto(
            @PathVariable Long id,
            @RequestBody Producto productoDetails) {

        Producto producto = productoRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Producto no encontrado")
            );

        producto.setProdu_nombre(productoDetails.getProdu_nombre());
        producto.setProdu_categoria(productoDetails.getProdu_categoria());
        producto.setProdu_precioUnitario(productoDetails.getProdu_precioUnitario());
        producto.setProdu_stock(productoDetails.getProdu_stock());
        producto.setProdu_stockMinimo(productoDetails.getProdu_stockMinimo());
        producto.setProdu_estado(productoDetails.getProdu_estado());

        return productoRepository.save(producto);
    }

    // 🔹 DELETE (borrado lógico)
    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id) {

        Producto producto = productoRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Producto no encontrado")
            );

        producto.setProdu_estado(false);
        productoRepository.save(producto);

        return "Producto desactivado con éxito";
    }
}
