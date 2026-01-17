package com.appsalon.backend.api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import com.appsalon.backend.api.Entities.*;
import com.appsalon.backend.api.Repositories.*;

@RestController
@RequestMapping("/detalle-venta")
public class DetalleVentaController {

    @Autowired private DetalleVentaRepository detalleVentaRepository;
    @Autowired private VentaRepository ventaRepository;
    @Autowired private ServicioRepository servicioRepository;
    @Autowired private ProductoRepository productoRepository;

    // 🔹 CREATE (SERVICIO o PRODUCTO)
    @PostMapping
    @Transactional
    public DetalleVenta createDetalleVenta(
            @RequestParam Long idVenta,
            @RequestParam(required = false) Long idServicio,
            @RequestParam(required = false) Long idProducto,
            @RequestBody DetalleVenta detalle) {

        // 🔐 Regla: SOLO servicio O producto
        if ((idServicio == null && idProducto == null) ||
            (idServicio != null && idProducto != null)) {
            throw new RuntimeException("Debe especificar solo Servicio o Producto");
        }

        Venta venta = ventaRepository.findById(idVenta)
            .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        if (venta.getVenta_total() == null) {
            venta.setVenta_total(BigDecimal.ZERO);
        }

        detalle.setVenta(venta);

        BigDecimal subtotal;

        // 🧾 SERVICIO
        if (idServicio != null) {
            Servicio servicio = servicioRepository.findById(idServicio)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

            detalle.setServicio(servicio);
            detalle.setDetalleventa_cantidad(1);
            detalle.setDetalleVenta_precio(servicio.getServ_precio());
            subtotal = servicio.getServ_precio();
        }
        // 📦 PRODUCTO
        else {
            Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            if (producto.getProdu_stock() < detalle.getDetalleventa_cantidad()) {
                throw new RuntimeException("Stock insuficiente");
            }

            producto.setProdu_stock(
                producto.getProdu_stock() - detalle.getDetalleventa_cantidad()
            );
            productoRepository.save(producto);

            detalle.setProducto(producto);
            detalle.setDetalleVenta_precio(producto.getProdu_precioUnitario());

            subtotal = producto.getProdu_precioUnitario()
                .multiply(BigDecimal.valueOf(detalle.getDetalleventa_cantidad()));
        }

        // 🔥 Actualizar total
        venta.setVenta_total(venta.getVenta_total().add(subtotal));
        ventaRepository.save(venta);

        return detalleVentaRepository.save(detalle);
    }

    // 🔹 DELETE (revierte stock y total)
    @DeleteMapping("/{id}")
    @Transactional
    public String deleteDetalleVenta(@PathVariable Long id) {

        DetalleVenta detalle = detalleVentaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Detalle de venta no encontrado"));

        Venta venta = detalle.getVenta();

        BigDecimal subtotal = detalle.getDetalleVenta_precio()
            .multiply(BigDecimal.valueOf(detalle.getDetalleventa_cantidad()));

        // 🔄 Revertir stock si era producto
        if (detalle.getProducto() != null) {
            Producto producto = detalle.getProducto();
            producto.setProdu_stock(
                producto.getProdu_stock() + detalle.getDetalleventa_cantidad()
            );
            productoRepository.save(producto);
        }

        // 🔄 Revertir total con seguridad
        BigDecimal nuevoTotal = venta.getVenta_total().subtract(subtotal);
        venta.setVenta_total(nuevoTotal.max(BigDecimal.ZERO));
        ventaRepository.save(venta);

        detalleVentaRepository.delete(detalle);

        return "Detalle de venta eliminado y cambios revertidos";
    }
}
