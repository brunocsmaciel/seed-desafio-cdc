package com.deveficiente.workshop.demo.controller;


import com.deveficiente.workshop.demo.domain.model.Categoria;
import com.deveficiente.workshop.demo.repository.CategoriaRepository;
import com.deveficiente.workshop.demo.controller.dto.CategoriaRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    // 1
    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    //2
    public ResponseEntity<Void> criarCategoria(@RequestBody @Valid CategoriaRequest request) {

        Optional<Categoria> categoriaOptional = categoriaRepository.findByNome(request.nome());

        if (categoriaOptional.isPresent()) {
            throw new IllegalArgumentException(String.format("Categoria com nome %s já criada", request.nome()));
        }
        //3
        categoriaRepository.save(request.toDomain());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
