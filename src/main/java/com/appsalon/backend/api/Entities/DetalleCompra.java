package com.appsalon.backend.api.Entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "detalle_compra")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DetalleCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detallecompra_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comp_id", nullable = false)
    @JsonIgnoreProperties({"proveedor", "negocio"})
    private Compra compra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produ_id", nullable = false)
    @JsonIgnoreProperties({"categoria", "negocio"})
    private Producto producto;

    @Column(nullable = false)
    private Integer comp_cantidad = 1;

    @Column(nullable = false, precision = 7, scale = 2)
    private BigDecimal comp_precioCompra;

    public DetalleCompra() {}

    public DetalleCompra(Long detallecompra_id, Compra compra, Producto producto,
                          Integer comp_cantidad, BigDecimal comp_precioCompra) {
        this.detallecompra_id = detallecompra_id;
        this.compra = compra;
        this.producto = producto;
        this.comp_cantidad = comp_cantidad;
        this.comp_precioCompra = comp_precioCompra;
    }

    public Long getDetallecompra_id() {
        return detallecompra_id;
    }

    public void setDetallecompra_id(Long detallecompra_id) {
        this.detallecompra_id = detallecompra_id;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getComp_cantidad() {
        return comp_cantidad;
    }

    public void setComp_cantidad(Integer comp_cantidad) {
        this.comp_cantidad = comp_cantidad;
    }

    public BigDecimal getComp_precioCompra() {
        return comp_precioCompra;
    }

    public void setComp_precioCompra(BigDecimal comp_precioCompra) {
        this.comp_precioCompra = comp_precioCompra;
    }
}
