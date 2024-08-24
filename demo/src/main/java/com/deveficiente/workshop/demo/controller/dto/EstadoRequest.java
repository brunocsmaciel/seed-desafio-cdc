package com.deveficiente.workshop.demo.controller.dto;

import com.deveficiente.workshop.demo.domain.model.Estado;
import com.deveficiente.workshop.demo.domain.model.Pais;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

public record EstadoRequest(String nome, java.lang.Long paisId) {

    public Estado toModel(EntityManager manager) {

        Pais pais = manager.find(Pais.class, paisId);

        if (pais == null) {
            throw new EntityNotFoundException("Pais nao encontrado");
        }
        return new Estado(nome, pais);
    }
}
