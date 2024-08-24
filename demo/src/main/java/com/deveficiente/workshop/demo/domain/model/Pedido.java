package com.deveficiente.workshop.demo.domain.model;

import com.deveficiente.workshop.demo.controller.Documento;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Compra compra;

    @ElementCollection
    @Size(min = 1)
    private Set<ItemPedido> itens = new HashSet<>();

    public Pedido(Compra compra, Set<ItemPedido> itens) {
        Assert.isTrue(!itens.isEmpty(), "todo pedido deve ter pelo menos um item");
        this.compra = compra;
        this.itens.addAll(itens);
    }

    public Pedido() {
    }

    public boolean totalIgual(BigDecimal total) {
        BigDecimal totalPedido = itens.stream()
                .map(ItemPedido::total)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalPedido.equals(total);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                ", itens=" + itens +
                '}';
    }

    public Compra getCompra() {
        return compra;
    }

    public Set<ItemPedido> getItens() {
        return itens;
    }
}
