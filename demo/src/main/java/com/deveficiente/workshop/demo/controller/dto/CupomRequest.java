package com.deveficiente.workshop.demo.controller.dto;

import com.deveficiente.workshop.demo.domain.model.Cupom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record CupomRequest(@NotNull String codigo,
                           @Positive @NotNull BigDecimal percentualDesconto,
                           @NotNull @Future LocalDate validadeCupom) {


    public Cupom toModel(EntityManager manager) {
        TypedQuery<Cupom> cupomQuery = manager.createQuery("select c from Cupom c where c.codigo = :codigo", Cupom.class);
        cupomQuery.setParameter("codigo", codigo);

        List<Cupom> cupons = cupomQuery.getResultList();

        if (!cupons.isEmpty()) {
            throw new UnsupportedOperationException("cupom ja cadastrado");
        }

        return new Cupom(codigo, percentualDesconto, validadeCupom);
    }
}
