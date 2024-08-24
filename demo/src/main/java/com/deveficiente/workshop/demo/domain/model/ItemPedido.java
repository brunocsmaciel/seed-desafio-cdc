package com.deveficiente.workshop.demo.domain.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class ItemPedido {

    @ManyToOne
    @NotNull
    private Livro livro;
    @Positive
    private int quantidade;
    @Positive
    private BigDecimal precoMomento;

    public ItemPedido() {
    }

    public ItemPedido(Livro livro, int quantidade) {
        this.livro = livro;
        this.quantidade = quantidade;
        this.precoMomento = livro.getPreco();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemPedido that = (ItemPedido) o;

        return Objects.equals(livro, that.livro);
    }

    @Override
    public int hashCode() {
        return livro != null ? livro.hashCode() : 0;
    }

    public BigDecimal total() {
        return precoMomento.multiply(new BigDecimal(quantidade));
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "livro=" + livro +
                ", quantidade=" + quantidade +
                ", precoMomento=" + precoMomento +
                '}';
    }
}
