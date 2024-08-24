package com.deveficiente.workshop.demo.controller;

import com.deveficiente.workshop.demo.domain.model.Autor;
import com.deveficiente.workshop.demo.repository.AutorRepository;
import com.deveficiente.workshop.demo.controller.dto.AutorRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autores")
public class AutorController {

    // 1 ICP
    private final AutorRepository autorRepository;

    public AutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @PostMapping
    // 1 ICP
    public ResponseEntity<Autor> criaAutor(@RequestBody @Valid AutorRequest request) {

        // 1 ICP
        var autorOptional = autorRepository.findByEmail(request.email());

        // 1 ICP
        if (autorOptional.isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        // 1 ICP
        autorRepository.save(request.toDomain());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
