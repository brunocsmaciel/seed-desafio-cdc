package com.deveficiente.workshop.demo.controller.dto;

import com.deveficiente.workshop.demo.domain.model.Compra;
import com.deveficiente.workshop.demo.domain.model.ItemPedido;
import com.deveficiente.workshop.demo.domain.model.Pedido;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public record PedidoRequest(@Positive @NotNull BigDecimal total,
                            @Size(min = 1) @Valid List<NovoPedidoItemRequest> itens) {

    public Function<Compra, Pedido> toModel(EntityManager manager) {

        Set<ItemPedido> itensCalculados = itens
                .stream()
                .map(item -> item.toModel(manager))
                .collect(Collectors.toSet());

        return (compra) -> {
            Pedido pedido = new Pedido(compra, itensCalculados);
            Assert.isTrue(pedido.totalIgual(total), "Total enviado não corresponde ao total real");
            return pedido;
        };

    }
}
