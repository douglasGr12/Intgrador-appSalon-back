package com.appsalon.backend.api.Entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "detalle_venta")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detalleVenta_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venta_id", nullable = false)
    @JsonIgnoreProperties({"cliente", "empleado", "negocio", "detalles"})
    private Venta venta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serv_id")
    @JsonIgnoreProperties({"categoria", "negocio"})
    private Servicio servicio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produ_id")
    @JsonIgnoreProperties({"categoria", "negocio"})
    private Producto producto;

    @Column(nullable = false)
    private Integer detalleventa_cantidad = 1;

    @Column(nullable = false, precision = 7, scale = 2)
    private BigDecimal detalleVenta_precio;

    public DetalleVenta() {}

    public DetalleVenta(Long detalleVenta_id, Venta venta, Servicio servicio,
                        Producto producto, Integer detalleventa_cantidad,
                        BigDecimal detalleVenta_precio) {
        this.detalleVenta_id = detalleVenta_id;
        this.venta = venta;
        this.servicio = servicio;
        this.producto = producto;
        this.detalleventa_cantidad = detalleventa_cantidad;
        this.detalleVenta_precio = detalleVenta_precio;
    }

    public Long getDetalleVenta_id() {
        return detalleVenta_id;
    }

    public void setDetalleVenta_id(Long detalleVenta_id) {
        this.detalleVenta_id = detalleVenta_id;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getDetalleventa_cantidad() {
        return detalleventa_cantidad;
    }

    public void setDetalleventa_cantidad(Integer detalleventa_cantidad) {
        this.detalleventa_cantidad = detalleventa_cantidad;
    }

    public BigDecimal getDetalleVenta_precio() {
        return detalleVenta_precio;
    }

    public void setDetalleVenta_precio(BigDecimal detalleVenta_precio) {
        this.detalleVenta_precio = detalleVenta_precio;
    }
}
