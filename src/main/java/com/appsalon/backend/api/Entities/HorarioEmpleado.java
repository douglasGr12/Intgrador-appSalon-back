package com.appsalon.backend.api.Entities;

import jakarta.persistence.*;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "horario_empleado")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class HorarioEmpleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long horario_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id", nullable = false)
    @JsonIgnoreProperties({"usuario", "negocio"})
    private Empleado empleado;

    @Column(nullable = false, length = 20)
    private String diaSemana;

    @Column(nullable = false)
    private LocalTime horaInicio;

    @Column(nullable = false)
    private LocalTime horaFin;

    @Column(nullable = false)
    private Boolean estado = true;

    @PrePersist
    @PreUpdate
    private void validarHorario() {
        if (horaInicio != null && horaFin != null && horaInicio.isAfter(horaFin)) {
            throw new IllegalArgumentException("Hora inicio > hora fin");
        }
    }

    public HorarioEmpleado() {}

    public HorarioEmpleado(Long horario_id, Empleado empleado,
                           String diaSemana, LocalTime horaInicio,
                           LocalTime horaFin, Boolean estado) {
        this.horario_id = horario_id;
        this.empleado = empleado;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = estado;
    }

    public Long getHorario_id() {
        return horario_id;
    }

    public void setHorario_id(Long horario_id) {
        this.horario_id = horario_id;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
