package com.appsalon.backend.api.Entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "venta")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long venta_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cli_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Empleado empleado;

    @Column(nullable = false)
    private LocalDateTime venta_fecha;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal venta_total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "negocio_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Negocio negocio;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // 🔥 rompe ciclo infinito
    private List<DetalleVenta> detalles;

    public Venta() {}

    public Venta(Long venta_id, Cliente cliente, Empleado empleado,
                 LocalDateTime venta_fecha, BigDecimal venta_total,
                 Negocio negocio, List<DetalleVenta> detalles) {
        this.venta_id = venta_id;
        this.cliente = cliente;
        this.empleado = empleado;
        this.venta_fecha = venta_fecha;
        this.venta_total = venta_total;
        this.negocio = negocio;
        this.detalles = detalles;
    }


    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Negocio getNegocio() {
        return negocio;
    }

    public void setNegocio(Negocio negocio) {
        this.negocio = negocio;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }

    public Long getVenta_id() {
        return venta_id;
    }

    public void setVenta_id(Long venta_id) {
        this.venta_id = venta_id;
    }

    public LocalDateTime getVenta_fecha() {
        return venta_fecha;
    }

    public void setVenta_fecha(LocalDateTime venta_fecha) {
        this.venta_fecha = venta_fecha;
    }

    public BigDecimal getVenta_total() {
        return venta_total;
    }

    public void setVenta_total(BigDecimal venta_total) {
        this.venta_total = venta_total;
    }
}
