package com.appsalon.backend.api.Entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "empleado")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emp_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usu_id", nullable = false)
    @JsonIgnoreProperties({"password", "roles", "negocio"})
    private Usuario usuario;

    @Column(length = 255)
    private String emp_cargo;

    @Column(length = 255)
    private String emp_disponibilidad;

    @Column(nullable = false)
    private Boolean emp_estado = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "negocio_id", nullable = false)
    @JsonIgnoreProperties({"empleados", "clientes"})
    private Negocio negocio;

    public Empleado() {}

    public Empleado(Long emp_id, Usuario usuario, String emp_cargo,
                    String emp_disponibilidad, Boolean emp_estado,
                    Negocio negocio) {
        this.emp_id = emp_id;
        this.usuario = usuario;
        this.emp_cargo = emp_cargo;
        this.emp_disponibilidad = emp_disponibilidad;
        this.emp_estado = emp_estado;
        this.negocio = negocio;
    }

    public Long getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Long emp_id) {
        this.emp_id = emp_id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getEmp_cargo() {
        return emp_cargo;
    }

    public void setEmp_cargo(String emp_cargo) {
        this.emp_cargo = emp_cargo;
    }

    public String getEmp_disponibilidad() {
        return emp_disponibilidad;
    }

    public void setEmp_disponibilidad(String emp_disponibilidad) {
        this.emp_disponibilidad = emp_disponibilidad;
    }

    public Boolean getEmp_estado() {
        return emp_estado;
    }

    public void setEmp_estado(Boolean emp_estado) {
        this.emp_estado = emp_estado;
    }

    public Negocio getNegocio() {
        return negocio;
    }

    public void setNegocio(Negocio negocio) {
        this.negocio = negocio;
    }
}
