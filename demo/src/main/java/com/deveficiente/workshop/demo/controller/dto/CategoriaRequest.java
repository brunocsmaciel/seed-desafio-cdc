package com.deveficiente.workshop.demo.controller.dto;

import com.deveficiente.workshop.demo.domain.model.Categoria;
import jakarta.validation.constraints.NotBlank;

public record CategoriaRequest(@NotBlank String nome) {

    public Categoria toDomain() {
        return new Categoria(nome);
    }
}
