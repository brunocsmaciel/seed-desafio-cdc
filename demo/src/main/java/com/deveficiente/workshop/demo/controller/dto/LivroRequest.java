package com.deveficiente.workshop.demo.controller.dto;

import com.deveficiente.workshop.demo.domain.model.Autor;
import com.deveficiente.workshop.demo.domain.model.Categoria;
import com.deveficiente.workshop.demo.domain.model.Livro;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.*;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape;

public record LivroRequest(@NotBlank String titulo,
                           @NotBlank @Size(max = 500) String resumo,
                           @NotBlank String sumario,
                           @NotNull @Min(value = 20) BigDecimal preco,
                           @NotNull @Min(value = 100) Long numeroPaginas,
                           @NotBlank String isbn,
                           @Future @JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING) LocalDate dataPublicacao,
                           @NotNull PaisRequest idCategoria,
                           @NotNull PaisRequest idAutor) {


    //5
    public Livro toModel(EntityManager manager) {
        //1
        Autor autor = manager.find(Autor.class, idAutor);
        //2
        Categoria categoria = manager.find(Categoria.class, idCategoria);

        //3
        Assert.state(autor != null, "Você está querendo cadastrar um livro para um autor inexistente");
        //4
        Assert.state(categoria != null, "Você está querendo cadastrar um livro para uma categoria inexistente");


        return new Livro(titulo,
                resumo,
                sumario,
                preco,
                numeroPaginas,
                isbn,
                dataPublicacao,
                categoria,
                autor);
    }
}
