package com.appsalon.backend.api.Entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cli_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usu_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDate cli_fechaRegistro = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "negocio_id", nullable = false)
    private Negocio negocio;

    // 🔹 Constructor vacío (OBLIGATORIO)
    public Cliente() {
        this.cli_fechaRegistro = LocalDate.now(); // emula DEFAULT GETDATE()
    }

    // 🔹 Constructor completo
    public Cliente(Long cli_id, Usuario usuario,
                   LocalDate cli_fechaRegistro, Negocio negocio) {
        this.cli_id = cli_id;
        this.usuario = usuario;
        this.cli_fechaRegistro = cli_fechaRegistro;
        this.negocio = negocio;
    }

    // 🔹 Getters & Setters
    public Long getCli_id() {
        return cli_id;
    }

    public void setCli_id(Long cli_id) {
        this.cli_id = cli_id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getCli_fechaRegistro() {
        return cli_fechaRegistro;
    }

    public void setCli_fechaRegistro(LocalDate cli_fechaRegistro) {
        this.cli_fechaRegistro = cli_fechaRegistro;
    }

    public Negocio getNegocio() {
        return negocio;
    }

    public void setNegocio(Negocio negocio) {
        this.negocio = negocio;
    }
}
