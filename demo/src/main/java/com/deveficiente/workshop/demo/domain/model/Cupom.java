package com.deveficiente.workshop.demo.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cupons")
public class Cupom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column
    public String codigo;

    @Column
    public BigDecimal percentual;

    @Column
    public LocalDate dataValidade;

    public Cupom(String codigo, BigDecimal percentual, LocalDate dataValidade) {
        this.codigo = codigo;
        this.percentual = percentual;
        this.dataValidade = dataValidade;
    }

    @Deprecated
    public Cupom(){}

    public String getCodigo() {
        return this.codigo;
    }

    public boolean ehValido() {
        return dataValidade.isAfter(LocalDate.now());
    }

    public BigDecimal getPercentualDesconto() {
        return percentual;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }
}
