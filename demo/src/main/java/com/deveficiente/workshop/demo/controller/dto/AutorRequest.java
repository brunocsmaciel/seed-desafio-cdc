package com.deveficiente.workshop.demo.controller.dto;

import com.deveficiente.workshop.demo.Autor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AutorRequest(@NotBlank String nome,
                           @Email @NotBlank String email,
                           @NotBlank @Size(max = 400) String descricao) {

    public Autor toDomain() {
        return new Autor(nome, email, descricao);
    }
}
