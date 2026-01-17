package com.appsalon.backend.api.Entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "servicio")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serv_id;

    @Column(nullable = false, length = 100)
    private String serv_nombre;

    @Column(nullable = false, precision = 7, scale = 2)
    private BigDecimal serv_precio;

    @Column(name = "serv_duracion_minutos", nullable = false)
    private Integer serv_duracionMinutos;

    @Column(nullable = false)
    private Boolean serv_estado = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "negocio_id", nullable = false)
    @JsonIgnoreProperties({"negocio_logo", "negocio_parametros"})
    private Negocio negocio;

    public Servicio() {}

    public Servicio(Long serv_id, String serv_nombre, BigDecimal serv_precio,
                    Integer serv_duracionMinutos, Boolean serv_estado,
                    Negocio negocio) {
        this.serv_id = serv_id;
        this.serv_nombre = serv_nombre;
        this.serv_precio = serv_precio;
        this.serv_duracionMinutos = serv_duracionMinutos;
        this.serv_estado = serv_estado;
        this.negocio = negocio;
    }

    public Long getServ_id() {
        return serv_id;
    }

    public void setServ_id(Long serv_id) {
        this.serv_id = serv_id;
    }

    public String getServ_nombre() {
        return serv_nombre;
    }

    public void setServ_nombre(String serv_nombre) {
        this.serv_nombre = serv_nombre;
    }

    public BigDecimal getServ_precio() {
        return serv_precio;
    }

    public void setServ_precio(BigDecimal serv_precio) {
        this.serv_precio = serv_precio;
    }

    public Integer getServ_duracionMinutos() {
        return serv_duracionMinutos;
    }

    public void setServ_duracionMinutos(Integer serv_duracionMinutos) {
        this.serv_duracionMinutos = serv_duracionMinutos;
    }

    public Boolean getServ_estado() {
        return serv_estado;
    }

    public void setServ_estado(Boolean serv_estado) {
        this.serv_estado = serv_estado;
    }

    public Negocio getNegocio() {
        return negocio;
    }

    public void setNegocio(Negocio negocio) {
        this.negocio = negocio;
    }
}
