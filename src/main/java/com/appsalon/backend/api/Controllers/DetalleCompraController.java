package com.appsalon.backend.api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.appsalon.backend.api.Entities.*;
import com.appsalon.backend.api.Repositories.*;

@RestController
@RequestMapping("/detalle-compra")
public class DetalleCompraController {

    @Autowired
    private DetalleCompraRepository detalleCompraRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // 🔹 GET ALL
    @GetMapping
    public List<DetalleCompra> getAllDetalleCompras() {
        return detalleCompraRepository.findAll();
    }

    // 🔹 CREATE + ACTUALIZA STOCK
    @PostMapping
    @Transactional
    public DetalleCompra createDetalleCompra(
            @RequestParam Long idCompra,
            @RequestParam Long idProducto,
            @RequestBody DetalleCompra detalle) {

        Compra compra = compraRepository.findById(idCompra)
            .orElseThrow(() -> new RuntimeException("Compra no encontrada"));

        Producto producto = productoRepository.findById(idProducto)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // 🔥 ACTUALIZAR STOCK
        producto.setProdu_stock(
            producto.getProdu_stock() + detalle.getComp_cantidad()
        );
        productoRepository.save(producto);

        detalle.setCompra(compra);
        detalle.setProducto(producto);

        return detalleCompraRepository.save(detalle);
    }

    // 🔹 DELETE (revierte stock)
    @DeleteMapping("/{id}")
    @Transactional
    public String deleteDetalleCompra(@PathVariable Long id) {

        DetalleCompra detalle = detalleCompraRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Detalle de compra no encontrado"));

        Producto producto = detalle.getProducto();

        // 🔥 REVERTIR STOCK (con seguridad)
        int nuevoStock = producto.getProdu_stock() - detalle.getComp_cantidad();
        producto.setProdu_stock(Math.max(nuevoStock, 0));

        productoRepository.save(producto);
        detalleCompraRepository.delete(detalle);

        return "Detalle de compra eliminado y stock revertido";
    }
}
