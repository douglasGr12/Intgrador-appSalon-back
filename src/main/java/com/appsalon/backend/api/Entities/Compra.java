package com.appsalon.backend.api.Entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "compra")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comp_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prove_id", nullable = false)
    @JsonIgnoreProperties({"compras"}) // 👈 evita loop Proveedor → Compra
    private Proveedor proveedor;

    @Column(nullable = false)
    private LocalDate comp_fechaCompra = LocalDate.now();

    @Column(length = 100)
    private String comp_comprobante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "negocio_id", nullable = false)
    @JsonIgnoreProperties({"compras"})
    private Negocio negocio;

    public Compra() {
        this.comp_fechaCompra = LocalDate.now();
    }

    public Compra(Long comp_id, Proveedor proveedor, LocalDate comp_fechaCompra,
                  String comp_comprobante, Negocio negocio) {
        this.comp_id = comp_id;
        this.proveedor = proveedor;
        this.comp_fechaCompra = comp_fechaCompra;
        this.comp_comprobante = comp_comprobante;
        this.negocio = negocio;
    }

    public Long getComp_id() {
        return comp_id;
    }

    public void setComp_id(Long comp_id) {
        this.comp_id = comp_id;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public LocalDate getComp_fechaCompra() {
        return comp_fechaCompra;
    }

    public void setComp_fechaCompra(LocalDate comp_fechaCompra) {
        this.comp_fechaCompra = comp_fechaCompra;
    }

    public String getComp_comprobante() {
        return comp_comprobante;
    }

    public void setComp_comprobante(String comp_comprobante) {
        this.comp_comprobante = comp_comprobante;
    }

    public Negocio getNegocio() {
        return negocio;
    }

    public void setNegocio(Negocio negocio) {
        this.negocio = negocio;
    }
}
