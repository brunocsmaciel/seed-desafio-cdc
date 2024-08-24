package com.deveficiente.workshop.demo.controller.dto;

import com.deveficiente.workshop.demo.domain.model.ItemPedido;
import com.deveficiente.workshop.demo.domain.model.Livro;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record NovoPedidoItemRequest(@NotNull Long idLivro,
                                    @Positive int quantidade) {
    public ItemPedido toModel(EntityManager manager) {

        return new ItemPedido(manager.find(Livro.class, idLivro), quantidade);
    }
}
