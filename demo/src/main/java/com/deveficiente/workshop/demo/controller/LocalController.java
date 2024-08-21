package com.deveficiente.workshop.demo.controller;

import com.deveficiente.workshop.demo.Estado;
import com.deveficiente.workshop.demo.Pais;
import com.deveficiente.workshop.demo.controller.dto.EstadoRequest;
import com.deveficiente.workshop.demo.controller.dto.PaisRequest;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/localizacao")
public class LocalController {

    private EntityManager manager;

    public LocalController(EntityManager manager) {
        this.manager = manager;
    }

    @PostMapping("/pais")
    @Transactional
    public ResponseEntity<Void> criarPais(@RequestBody @Valid PaisRequest request) {

        Pais pais = request.toModel();
        manager.persist(pais);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/estado")
    @Transactional
    public ResponseEntity<Void> criarEstado(@RequestBody @Valid EstadoRequest request) {

        Estado estado = request.toModel(manager);
        manager.persist(estado);

        return ResponseEntity.ok().build();
    }
}
