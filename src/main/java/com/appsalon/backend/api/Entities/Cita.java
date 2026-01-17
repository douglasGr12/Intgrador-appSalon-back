package com.appsalon.backend.api.Entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "cita")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cita_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cli_id", nullable = false)
    @JsonIgnoreProperties({"usuario", "negocio"}) // 👈 evita loops
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serv_id", nullable = false)
    private Servicio servicio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    private Empleado empleado; // opcional

    @Column(nullable = false)
    private LocalDate cita_fechaCita;

    @Column(nullable = false)
    private LocalTime cita_horaCita;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private EstadoCita cita_estado = EstadoCita.PROGRAMADA;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "negocio_id", nullable = false)
    private Negocio negocio;

    public Cita() {
    }

    public Cita(Long cita_id, Cliente cliente, Servicio servicio, Empleado empleado,
                LocalDate cita_fechaCita, LocalTime cita_horaCita,
                EstadoCita cita_estado, Negocio negocio) {
        this.cita_id = cita_id;
        this.cliente = cliente;
        this.servicio = servicio;
        this.empleado = empleado;
        this.cita_fechaCita = cita_fechaCita;
        this.cita_horaCita = cita_horaCita;
        this.cita_estado = cita_estado;
        this.negocio = negocio;
    }

    public Long getCita_id() {
        return cita_id;
    }

    public void setCita_id(Long cita_id) {
        this.cita_id = cita_id;
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

    public LocalDate getCita_fechaCita() {
        return cita_fechaCita;
    }

    public void setCita_fechaCita(LocalDate cita_fechaCita) {
        this.cita_fechaCita = cita_fechaCita;
    }

    public LocalTime getCita_horaCita() {
        return cita_horaCita;
    }

    public void setCita_horaCita(LocalTime cita_horaCita) {
        this.cita_horaCita = cita_horaCita;
    }

    public EstadoCita getCita_estado() {
        return cita_estado;
    }

    public void setCita_estado(EstadoCita cita_estado) {
        this.cita_estado = cita_estado;
    }

    public Negocio getNegocio() {
        return negocio;
    }

    public void setNegocio(Negocio negocio) {
        this.negocio = negocio;
    }
}
