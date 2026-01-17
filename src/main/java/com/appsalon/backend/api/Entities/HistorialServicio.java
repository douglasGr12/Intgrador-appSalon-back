package com.appsalon.backend.api.Entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "historial_servicio")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class HistorialServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historial_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cli_id", nullable = false)
    @JsonIgnoreProperties({"usuario", "negocio"})
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serv_id", nullable = false)
    @JsonIgnoreProperties({"categoria", "negocio"})
    private Servicio servicio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id", nullable = false)
    @JsonIgnoreProperties({"usuario", "negocio"})
    private Empleado empleado;

    @Column(nullable = false)
    private LocalDate historial_fecha = LocalDate.now();

    @Column(length = 255)
    private String historial_detalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "negocio_id", nullable = false)
    @JsonIgnoreProperties({"empleados", "clientes"})
    private Negocio negocio;

    public HistorialServicio() {}

    public HistorialServicio(Long historial_id, Cliente cliente,
                             Servicio servicio, Empleado empleado,
                             LocalDate historial_fecha,
                             String historial_detalle,
                             Negocio negocio) {
        this.historial_id = historial_id;
        this.cliente = cliente;
        this.servicio = servicio;
        this.empleado = empleado;
        this.historial_fecha = historial_fecha;
        this.historial_detalle = historial_detalle;
        this.negocio = negocio;
    }

    public Long getHistorial_id() {
        return historial_id;
    }

    public void setHistorial_id(Long historial_id) {
        this.historial_id = historial_id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public LocalDate getHistorial_fecha() {
        return historial_fecha;
    }

    public void setHistorial_fecha(LocalDate historial_fecha) {
        this.historial_fecha = historial_fecha;
    }

    public String getHistorial_detalle() {
        return historial_detalle;
    }

    public void setHistorial_detalle(String historial_detalle) {
        this.historial_detalle = historial_detalle;
    }

    public Negocio getNegocio() {
        return negocio;
    }

    public void setNegocio(Negocio negocio) {
        this.negocio = negocio;
    }
}
