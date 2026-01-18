package com.appsalon.backend.api.Entities;

import jakarta.persistence.*;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "negocio")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Negocio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long negocio_id;

    @Column(nullable = false)
    private String negocio_nombre;

    @Column(nullable = false)
    private LocalTime negocio_apertura;

    @Column(nullable = false)
    private LocalTime negocio_cierre;

    @Lob
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // 🔥 evita enviar blobs enormes por la API
    private byte[] negocio_logo;

    @Column(columnDefinition = "TEXT")
    private String negocio_parametros;

    public Negocio() {}

    public Negocio(Long negocio_id, String negocio_nombre,
                   LocalTime negocio_apertura, LocalTime negocio_cierre,
                   byte[] negocio_logo, String negocio_parametros) {
        this.negocio_id = negocio_id;
        this.negocio_nombre = negocio_nombre;
        this.negocio_apertura = negocio_apertura;
        this.negocio_cierre = negocio_cierre;
        this.negocio_logo = negocio_logo;
        this.negocio_parametros = negocio_parametros;
    }

    public Long getNegocio_id() {
        return negocio_id;
    }

    public void setNegocio_id(Long negocio_id) {
        this.negocio_id = negocio_id;
    }

    public String getNegocio_nombre() {
        return negocio_nombre;
    }

    public void setNegocio_nombre(String negocio_nombre) {
        this.negocio_nombre = negocio_nombre;
    }

    public LocalTime getNegocio_apertura() {
        return negocio_apertura;
    }

    public void setNegocio_apertura(LocalTime negocio_apertura) {
        this.negocio_apertura = negocio_apertura;
    }

    public LocalTime getNegocio_cierre() {
        return negocio_cierre;
    }

    public void setNegocio_cierre(LocalTime negocio_cierre) {
        this.negocio_cierre = negocio_cierre;
    }

    public byte[] getNegocio_logo() {
        return negocio_logo;
    }

    public void setNegocio_logo(byte[] negocio_logo) {
        this.negocio_logo = negocio_logo;
    }

    public String getNegocio_parametros() {
        return negocio_parametros;
    }

    public void setNegocio_parametros(String negocio_parametros) {
        this.negocio_parametros = negocio_parametros;
    }
}
