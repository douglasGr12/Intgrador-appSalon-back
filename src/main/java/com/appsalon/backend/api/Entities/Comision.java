package com.appsalon.backend.api.Entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "comision")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comi_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id", nullable = false)
    @JsonIgnoreProperties({"usuario", "negocio"})
    private Empleado empleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venta_id", nullable = false)
    @JsonIgnoreProperties({"detalles"})
    private Venta venta;

    @Column(nullable = false, precision = 7, scale = 2)
    private BigDecimal comi_monto;

    @Column(nullable = false)
    private LocalDate comi_fechaGenerada = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "negocio_id", nullable = false)
    private Negocio negocio;

    public Comision() {
        this.comi_fechaGenerada = LocalDate.now();
    }

    public Comision(Long comi_id, Empleado empleado, Venta venta,
                    BigDecimal comi_monto, LocalDate comi_fechaGenerada,
                    Negocio negocio) {
        this.comi_id = comi_id;
        this.empleado = empleado;
        this.venta = venta;
        this.comi_monto = comi_monto;
        this.comi_fechaGenerada = comi_fechaGenerada;
        this.negocio = negocio;
    }

    public Long getComi_id() {
        return comi_id;
    }

    public void setComi_id(Long comi_id) {
        this.comi_id = comi_id;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public BigDecimal getComi_monto() {
        return comi_monto;
    }

    public void setComi_monto(BigDecimal comi_monto) {
        this.comi_monto = comi_monto;
    }

    public LocalDate getComi_fechaGenerada() {
        return comi_fechaGenerada;
    }

    public void setComi_fechaGenerada(LocalDate comi_fechaGenerada) {
        this.comi_fechaGenerada = comi_fechaGenerada;
    }

    public Negocio getNegocio() {
        return negocio;
    }

    public void setNegocio(Negocio negocio) {
        this.negocio = negocio;
    }
}
