package com.deveficiente.workshop.demo.controller.dto;

import com.deveficiente.workshop.demo.Estado;
import com.deveficiente.workshop.demo.Pais;
import jakarta.persistence.EntityManager;

public record EstadoRequest(String nome, Long paisId) {

    public Estado toModel(EntityManager manager) {

        Pais pais = manager.find(Pais.class, paisId);

        if (pais == null) {
            throw new RuntimeException("Pais nao encontrado");
        }
        return new Estado(nome, pais);
    }
}
