package com.appsalon.backend.api.Entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "proveedor")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prove_id;

    @Column(nullable = false, length = 120)
    private String prove_nombre;

    @Column(length = 15)
    private String prove_telefono;

    @Column(length = 80, unique = true)
    private String prove_correo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "negocio_id", nullable = false)
    @JsonIgnoreProperties({"negocio_logo", "negocio_parametros"})
    private Negocio negocio;

    public Proveedor() {}

    public Proveedor(Long prove_id, String prove_nombre,
                     String prove_telefono, String prove_correo,
                     Negocio negocio) {
        this.prove_id = prove_id;
        this.prove_nombre = prove_nombre;
        this.prove_telefono = prove_telefono;
        this.prove_correo = prove_correo;
        this.negocio = negocio;
    }

    public Long getProve_id() {
        return prove_id;
    }

    public void setProve_id(Long prove_id) {
        this.prove_id = prove_id;
    }

    public String getProve_nombre() {
        return prove_nombre;
    }

    public void setProve_nombre(String prove_nombre) {
        this.prove_nombre = prove_nombre;
    }

    public String getProve_telefono() {
        return prove_telefono;
    }

    public void setProve_telefono(String prove_telefono) {
        this.prove_telefono = prove_telefono;
    }

    public String getProve_correo() {
        return prove_correo;
    }

    public void setProve_correo(String prove_correo) {
        this.prove_correo = prove_correo;
    }

    public Negocio getNegocio() {
        return negocio;
    }

    public void setNegocio(Negocio negocio) {
        this.negocio = negocio;
    }
}
