package com.deveficiente.workshop.demo.controller.dto;

import com.deveficiente.workshop.demo.Pais;
import jakarta.validation.constraints.NotBlank;

public record PaisRequest(@NotBlank String nome) {


    public Pais toModel() {
        return new Pais(nome);
    }
}
