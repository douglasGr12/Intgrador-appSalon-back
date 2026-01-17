package com.appsalon.backend.api.Entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "producto")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long produ_id;

    @Column(nullable = false, length = 100)
    private String produ_nombre;

    @Column(length = 50)
    private String produ_categoria;

    @Column(name = "produ_precio_unitario", precision = 7, scale = 2)
    private BigDecimal produ_precioUnitario;

    @Column(nullable = false)
    private Integer produ_stock = 0;

    @Column(nullable = false)
    private Integer produ_stockMinimo = 0;

    @Column(nullable = false)
    private Boolean produ_estado = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "negocio_id", nullable = false)
    @JsonIgnoreProperties({"negocio_logo", "negocio_parametros"})
    private Negocio negocio;

    public Producto() {}

    public Producto(Long produ_id, String produ_nombre, String produ_categoria,
                    BigDecimal produ_precioUnitario, Integer produ_stock,
                    Integer produ_stockMinimo, Boolean produ_estado,
                    Negocio negocio) {
        this.produ_id = produ_id;
        this.produ_nombre = produ_nombre;
        this.produ_categoria = produ_categoria;
        this.produ_precioUnitario = produ_precioUnitario;
        this.produ_stock = produ_stock;
        this.produ_stockMinimo = produ_stockMinimo;
        this.produ_estado = produ_estado;
        this.negocio = negocio;
    }

    public Long getProdu_id() {
        return produ_id;
    }

    public void setProdu_id(Long produ_id) {
        this.produ_id = produ_id;
    }

    public String getProdu_nombre() {
        return produ_nombre;
    }

    public void setProdu_nombre(String produ_nombre) {
        this.produ_nombre = produ_nombre;
    }

    public String getProdu_categoria() {
        return produ_categoria;
    }

    public void setProdu_categoria(String produ_categoria) {
        this.produ_categoria = produ_categoria;
    }

    public BigDecimal getProdu_precioUnitario() {
        return produ_precioUnitario;
    }

    public void setProdu_precioUnitario(BigDecimal produ_precioUnitario) {
        this.produ_precioUnitario = produ_precioUnitario;
    }

    public Integer getProdu_stock() {
        return produ_stock;
    }

    public void setProdu_stock(Integer produ_stock) {
        this.produ_stock = produ_stock;
    }

    public Integer getProdu_stockMinimo() {
        return produ_stockMinimo;
    }

    public void setProdu_stockMinimo(Integer produ_stockMinimo) {
        this.produ_stockMinimo = produ_stockMinimo;
    }

    public Boolean getProdu_estado() {
        return produ_estado;
    }

    public void setProdu_estado(Boolean produ_estado) {
        this.produ_estado = produ_estado;
    }

    public Negocio getNegocio() {
        return negocio;
    }

    public void setNegocio(Negocio negocio) {
        this.negocio = negocio;
    }
}
